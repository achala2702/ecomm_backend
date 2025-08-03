package com.achala.order_server.client;

import com.achala.order_server.dto.UserValidateResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-SERVER")
public interface AuthServerClient {

    @GetMapping("/auth/validate")
    ResponseEntity<UserValidateResponseDto> validateUser(@RequestHeader("Cookie") String cookieHeader);
}
