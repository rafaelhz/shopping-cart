package com.shoppingcart.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @JmsListener(destination = "${queues.payment}")
    public void processPayment(Payment payment) {
        repository.save(payment);
    }
}
