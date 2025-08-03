package com.achala.order_server.dto;

import com.achala.order_server.enums.PaymentMethod;
import com.achala.order_server.model.OrderItem;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponseDto(
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Integer customerId,
        List<ProductPurchaseResponseDto> orderItems,
        LocalDateTime createdAt
) {
}
