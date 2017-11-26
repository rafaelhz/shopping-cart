package com.shoppingcart.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "${queues.payment}")
    public void processPayment(Payment payment) {
        repository.save(payment);
    }
}
