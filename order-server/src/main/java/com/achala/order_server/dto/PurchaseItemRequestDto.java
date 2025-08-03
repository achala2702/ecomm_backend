package com.achala.order_server.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseItemRequestDto(

        @NotNull(message = "product is required")
        Integer productId,
        @Positive(message = "Quantity is required")
        Integer quantity
) {
}
