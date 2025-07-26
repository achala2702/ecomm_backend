package com.achala2702.auth_server.controller;

import com.achala2702.auth_server.dto.UserLoginRequestDto;
import com.achala2702.auth_server.dto.UserLoginResponseDto;
import com.achala2702.auth_server.dto.UserRegisterRequestDto;
import com.achala2702.auth_server.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterRequestDto userRegisterDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.userLogin(userLoginRequestDto));
    }
}
