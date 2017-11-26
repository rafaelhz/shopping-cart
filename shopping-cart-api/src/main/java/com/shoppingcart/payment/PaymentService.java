package com.shoppingcart.payment;

import com.shoppingcart.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${queues.payment}")
    private String paymentQueue;

    public void send(Payment payment, Order order) {
        payment.setOrderData(order);
        jmsTemplate.convertAndSend(paymentQueue, payment);
    }
}
