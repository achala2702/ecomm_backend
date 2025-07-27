package com.achala.product_server.dto;

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
