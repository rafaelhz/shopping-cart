package com.shoppingcart.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shoppingcart.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @JoinColumn(name = "fk_order", foreignKey = @ForeignKey(name = "fk_order_item_order"),
                referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "fk_product", foreignKey = @ForeignKey(name = "fk_order_item_product"),
                referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;
}
