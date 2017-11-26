package com.shoppingcart.config;

import com.shoppingcart.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class QueueErrorHandler implements ErrorHandler {

    private static Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Override
    public void handleError(Throwable t) {
        log.error("Error processing payment " + t.getCause().getMessage());
    }
}
