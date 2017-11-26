package com.shoppingcart.payment;

import lombok.Data;
import com.shoppingcart.order.Order;

import java.math.BigDecimal;

@Data
public class Payment {

    private String clientName;
    private String creditCardNumber;
    private Integer creditCardExpirationMonth;
    private Integer creditCardExpirationYear;
    private Integer orderId;
    private BigDecimal amount;

    public void setOrderData(Order order) {
        this.orderId = order.getId();
        this.amount = order.getTotal();
    }
}
