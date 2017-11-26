package com.shoppingcart.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shoppingcart.order.Order;
import com.shoppingcart.payment.Payment;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderPost {

    @NotNull
    private String clientName;
    @NotNull
    private String creditCardNumber;
    @NotNull
    private Integer creditCardExpirationMonth;
    @NotNull
    private Integer creditCardExpirationYear;
    @NotEmpty
    private List<OrderItemPost> items;

    @JsonIgnore
    public Order getOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setClientName(clientName);
        order.setItems(
                items
                        .stream()
                        .map(i -> i.convertToOrderItem(order))
                        .collect(Collectors.toList())
        );
        return order;
    }

    @JsonIgnore
    public Payment getPayment() {
        Payment payment = new Payment();
        payment.setClientName(clientName);
        payment.setCreditCardNumber(creditCardNumber);
        payment.setCreditCardExpirationMonth(creditCardExpirationMonth);
        payment.setCreditCardExpirationYear(creditCardExpirationYear);
        return payment;
    }
}
