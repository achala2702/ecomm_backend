package com.achala.order_server.util;

import com.achala.order_server.dto.OrderRequestDto;
import com.achala.order_server.dto.PurchaseItemRequestDto;
import com.achala.order_server.model.OrderItem;
import com.achala.order_server.model.OrderModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    //maps OrderRequestDto to OrderModel
    public OrderModel mapToOrderModel(OrderRequestDto request) {
        return OrderModel.builder()
                .reference(request.reference())
                .totalAmount(request.totalAmount())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .orderItems(this.mapToOrderItem(request.orderItems()))
                .build();
    }

    //maps List<PurchaseItemsRequestDto> to List<OrderItem>
    public List<OrderItem> mapToOrderItem(List<PurchaseItemRequestDto> requestItems) {
     return requestItems.stream()
             .map(item->OrderItem.builder()
                     .productId(item.productId())
                     .quantity(item.quantity())
             .build())
             .collect(Collectors.toList());
    }
}
