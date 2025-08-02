package com.achala.order_server.client;

import com.achala.order_server.dto.UserValidateResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "auth-server",
        url = "${application.config.auth-url}"
)
public interface AuthServerClient {

    @GetMapping("/validate")
    ResponseEntity<UserValidateResponseDto> validateUser(@RequestHeader("Cookie") String cookieHeader);
}
