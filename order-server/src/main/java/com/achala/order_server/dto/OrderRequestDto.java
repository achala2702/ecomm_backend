package com.achala.order_server.dto;

import com.achala.order_server.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequestDto(

        Integer orderId,
        String reference,
        @Positive(message = "total amount must be positive")
        BigDecimal totalAmount,
        @NotNull(message = "payment method required")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer id required")
        Integer customerId,
        @NotEmpty(message = "at least one order item required")
        List<PurchaseItemRequestDto> orderItems
) {
}
