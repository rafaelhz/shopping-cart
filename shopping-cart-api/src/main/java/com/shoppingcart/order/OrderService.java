package com.shoppingcart.order;

import com.shoppingcart.payment.Payment;
import com.shoppingcart.payment.PaymentService;
import com.shoppingcart.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentService paymentService;

    public Integer processOrder(Order order, Payment payment) {
        setOrderItemPrices(order);
        order.calculateTotal();
        order = repository.save(order);
        paymentService.send(payment, order);
        return order.getId();
    }

    private void setOrderItemPrices(Order order) {
        order
                .getItems()
                .forEach(i ->
                        i.setPrice(productRepository.findOne(i.getProduct().getId()).getPrice()));
    }
}
