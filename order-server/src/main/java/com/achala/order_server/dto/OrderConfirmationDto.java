package com.achala.order_server.dto;

import com.achala.order_server.enums.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderConfirmationDto(
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        UserValidateResponseDto customer,
        List<ProductPurchaseResponseDto> orderItems,
        LocalDateTime createdAt
) {
}
