package com.shoppingcar.payment;

import com.shoppingcart.Application;
import com.shoppingcart.payment.Payment;
import com.shoppingcart.payment.PaymentRepository;
import com.shoppingcart.payment.PaymentService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void shouldSaveAPayment() throws Exception {
        paymentService.processPayment(payment());

        List<Payment> payments = Lists.newArrayList(paymentRepository.findAll());

        assertEquals(1, payments.size());
        assertEquals("client test", payments.get(0).getClientName());
    }

    private Payment payment() {
        Payment payment = new Payment();
        payment.setOrderId(1);
        payment.setClientName("client test");
        payment.setCreditCardNumber("999999999");
        payment.setCreditCardExpirationMonth(10);
        payment.setCreditCardExpirationYear(2010);
        payment.setAmount(BigDecimal.valueOf(200));
        return payment;
    }
}
