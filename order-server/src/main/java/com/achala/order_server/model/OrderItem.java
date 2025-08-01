package com.achala.order_server.model;

import jakarta.persistence.*;

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderModel orderItem;
    private Integer productId;
    private Integer quantity;
}
