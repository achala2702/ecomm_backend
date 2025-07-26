package com.achala2702.auth_server.util;

import com.achala2702.auth_server.dto.UserRegisterRequestDto;
import com.achala2702.auth_server.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserModel mapToUserModel(UserRegisterRequestDto requestDto) {

        return UserModel.builder()
                .email(requestDto.email())
                .password(passwordEncoder.encode(requestDto.password()))
                .firstName(requestDto.firstName())
                .lastName(requestDto.lastName())
                .userAddress(requestDto.userAddress())
                .build();
    }
}
