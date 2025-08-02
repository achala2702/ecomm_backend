package com.achala.order_server.controller;

import com.achala.order_server.dto.OrderRequestDto;
import com.achala.order_server.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Integer> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.CreateOrder(orderRequestDto, request));
    }
}
