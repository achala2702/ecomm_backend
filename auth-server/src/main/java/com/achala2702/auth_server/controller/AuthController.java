package com.achala2702.auth_server.controller;

import com.achala2702.auth_server.dto.UserRegisterDto;
import com.achala2702.auth_server.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(UserRegisterDto userRegisterDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.registerUser(userRegisterDto));
    }
}
