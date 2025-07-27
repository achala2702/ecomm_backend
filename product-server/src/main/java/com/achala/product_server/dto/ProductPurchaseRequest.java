package com.achala.product_server.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product Id required!")
        Integer productId,
        @NotNull(message = "Quantity required;")
        Integer quantity
) {
}
