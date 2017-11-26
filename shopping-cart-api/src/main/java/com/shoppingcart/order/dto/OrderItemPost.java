package com.shoppingcart.order.dto;

import com.shoppingcart.order.Order;
import com.shoppingcart.order.OrderItem;
import com.shoppingcart.product.Product;
import lombok.Data;

@Data
public class OrderItemPost {

    private Product product;
    private Integer quantity;

    public OrderItem convertToOrderItem(Order order) {
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        return item;
    }
}
