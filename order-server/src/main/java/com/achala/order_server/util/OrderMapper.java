package com.achala.order_server.util;

import com.achala.order_server.dto.*;
import com.achala.order_server.model.OrderItem;
import com.achala.order_server.model.OrderModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    //maps OrderRequestDto to OrderModel
    public OrderModel mapToOrderModel(OrderRequestDto request, Integer userId, BigDecimal totalAmount) {
        OrderModel order = OrderModel.builder()
                .reference(request.reference())
                .totalAmount(totalAmount)
                .paymentMethod(request.paymentMethod())
                .customerId(userId)
                .build();

        //setting order items to order model
        List<OrderItem> orderItems = this.mapToOrderItem(request.orderItems(), order);
        order.setOrderItems(orderItems);

        return order;
    }

    //maps List<PurchaseItemsRequestDto> to List<OrderItem>
    public List<OrderItem> mapToOrderItem(List<PurchaseItemRequestDto> requestItems, OrderModel order) {
     return requestItems.stream()
             .map(item -> OrderItem.builder()
                     .productId(item.productId())
                     .quantity(item.quantity())
                     .orderItem(order)
                     .build())
             .collect(Collectors.toList());
    }

    //map order model to order responseDto
    public OrderResponseDto mapToOrderResponseDto(OrderModel orderModel, List<ProductPurchaseResponseDto> purchasedItems) {
        return OrderResponseDto.builder()
                .customerId(orderModel.getCustomerId())
                .reference(orderModel.getReference())
                .paymentMethod(orderModel.getPaymentMethod())
                .totalAmount(orderModel.getTotalAmount())
                .orderItems(purchasedItems)
                .createdAt(orderModel.getCreatedAt())
                .build();
    }

    //map to order confirmation dto
    public OrderConfirmationDto mapToOrderConfirmationDto(UserValidateResponseDto customer, OrderModel orderModel, List<ProductPurchaseResponseDto> purchasedItems) {
        return OrderConfirmationDto.builder()
                .customer(customer)
                .paymentMethod(orderModel.getPaymentMethod())
                .orderItems(purchasedItems)
                .totalAmount(orderModel.getTotalAmount())
                .reference(orderModel.getReference())
                .createdAt(orderModel.getCreatedAt())
                .build();
    }
}
