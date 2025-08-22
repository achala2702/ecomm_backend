package com.achala.notification_server.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponseDto(
        Integer productId,
        String name,
        String description,
        Integer quantity,
        BigDecimal price
) {
}
