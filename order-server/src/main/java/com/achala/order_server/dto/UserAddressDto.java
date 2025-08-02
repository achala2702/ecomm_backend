package com.achala.order_server.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record UserAddressDto(
        String houseNo,
        String street,
        String city,
        String zipCode
) {
}
