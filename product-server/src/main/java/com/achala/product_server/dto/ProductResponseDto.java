package com.achala.product_server.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponseDto(
        Integer productId,
        String name,
        String description,
        Integer availableQuantity,
        BigDecimal price,
        String category
) {
}
