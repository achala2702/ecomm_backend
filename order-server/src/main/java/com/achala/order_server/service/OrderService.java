package com.achala.order_server.service;

import com.achala.order_server.client.AuthServerClient;
import com.achala.order_server.dto.OrderRequestDto;
import com.achala.order_server.dto.UserValidateResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final AuthServerClient authServerClient;

    public Integer CreateOrder(OrderRequestDto orderRequestDto, HttpServletRequest request) {

        String cookieHeader = request.getHeader("Cookie");
        ResponseEntity<UserValidateResponseDto> authResponse = authServerClient.validateUser(cookieHeader);

        //need to handle error response coming through auth server open feign
        System.out.println(authResponse);

        return null;
    }
}
