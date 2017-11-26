package com.shoppingcart.order;

import com.shoppingcart.order.dto.OrderPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public Integer save(@Validated @RequestBody OrderPost orderPost) {
        return service.processOrder(
                orderPost.getOrder(),
                orderPost.getPayment());
    }
}
