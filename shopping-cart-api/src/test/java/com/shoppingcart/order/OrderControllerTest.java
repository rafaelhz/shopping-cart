package com.shoppingcart.order;

import com.shoppingcart.order.dto.OrderPost;
import com.shoppingcart.order.dto.OrderItemPost;
import com.shoppingcart.payment.PaymentService;
import com.shoppingcart.product.Product;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import helpers.TestsHelper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private OrderRepository orderRepository;
    @MockBean
    private PaymentService paymentMqSender;

    @Test
    public void shouldSaveAnOrder() throws Exception {
        OrderPost orderPost = orderPost();

        mvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.convertObjectToJsonBytes(orderPost)))
                .andExpect(status().isOk());

        List<Order> orders = Lists.newArrayList(orderRepository.findAll());

        assertEquals(1, orders.size());
        assertEquals("Client test", orders.get(0).getClientName());
        assertEquals(2, orders.get(0).getItems().size());
        assertEquals("350.40", orders.get(0).getTotal().toString());
    }

    @Test
    public void shouldValidateTheOrder() throws Exception {
        OrderPost emptyOrder = new OrderPost();

        mvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.convertObjectToJsonBytes(emptyOrder)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldSendThePaymentToTheMq() throws Exception {
        mvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.convertObjectToJsonBytes(orderPost())))
                .andExpect(status().isOk());

        verify(paymentMqSender, times(1)).send(any(), any());
    }

    private OrderPost orderPost() {
        OrderPost order = new OrderPost();
        order.setClientName("Client test");
        order.setCreditCardExpirationMonth(8);
        order.setCreditCardExpirationYear(1986);
        order.setCreditCardNumber("23456789");
        order.setItems(Arrays.asList(
                item(1, 10),
                item(2, 5)
        ));
        return order;
    }

    private OrderItemPost item(int productId, int quantity) {
        OrderItemPost orderProduct = new OrderItemPost();
        orderProduct.setProduct(new Product());
        orderProduct.getProduct().setId(productId);
        orderProduct.setQuantity(quantity);
        return orderProduct;
    }
}
