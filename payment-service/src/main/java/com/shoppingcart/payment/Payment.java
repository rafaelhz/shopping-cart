package com.shoppingcart.payment;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String creditCardNumber;

    @Column(nullable = false)
    private Integer creditCardExpirationMonth;

    @Column(nullable = false)
    private Integer creditCardExpirationYear;

    @Column(nullable = false)
    private Integer orderId;

    @Column(nullable = false)
    private BigDecimal amount;
}
