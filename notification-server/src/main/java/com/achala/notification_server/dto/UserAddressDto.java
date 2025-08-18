package com.achala.notification_server.dto;

public record UserAddressDto(
        String houseNo,
        String street,
        String city,
        String zipCode
) {
}
