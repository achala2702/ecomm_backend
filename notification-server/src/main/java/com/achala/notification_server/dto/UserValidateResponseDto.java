package com.achala.notification_server.dto;

import lombok.Builder;

@Builder
public record UserValidateResponseDto(
        Integer userId,
        String email,
        String firstName,
        String lastName,
        UserAddressDto userAddress
) {
}
