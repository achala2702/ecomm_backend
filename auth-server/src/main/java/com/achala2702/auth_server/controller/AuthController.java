package com.achala2702.auth_server.controller;

import com.achala2702.auth_server.dto.UserLoginRequestDto;
import com.achala2702.auth_server.dto.UserLoginResponseDto;
import com.achala2702.auth_server.dto.UserRegisterRequestDto;
import com.achala2702.auth_server.dto.UserValidateResponseDto;
import com.achala2702.auth_server.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.userLogin(userLoginRequestDto, response));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserValidateResponseDto> userValidate(@CookieValue("jwt") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.userValidation(token));
    }
}
