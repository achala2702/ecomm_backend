package com.achala2702.auth_server.dto;

import com.achala2702.auth_server.model.UserAddress;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        UserAddress userAddress
) {
}
