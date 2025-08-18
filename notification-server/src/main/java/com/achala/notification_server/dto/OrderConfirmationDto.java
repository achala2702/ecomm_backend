package com.achala.notification_server.dto;

import com.achala.notification_server.enums.PaymentMethod;
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
