package com.achala2702.auth_server.dto;

import com.achala2702.auth_server.model.UserAddress;
import lombok.Builder;

@Builder
public record UserLoginResponseDto(
        String email,
        String firstName,
        String lastName,
        UserAddress userAddress,
        String token
) {
}
