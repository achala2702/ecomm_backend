package com.achala.order_server.service;

import com.achala.order_server.client.AuthServerClient;
import com.achala.order_server.client.ProductServerClient;
import com.achala.order_server.dto.OrderRequestDto;
import com.achala.order_server.dto.ProductPurchaseResponseDto;
import com.achala.order_server.dto.UserValidateResponseDto;
import com.achala.order_server.model.OrderModel;
import com.achala.order_server.repository.OrderRepository;
import com.achala.order_server.util.OrderMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final AuthServerClient authServerClient;
    private final ProductServerClient productServerClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Integer CreateOrder(OrderRequestDto orderRequestDto, HttpServletRequest request) {

        //validating the user and get user information form auth server
        String cookieHeader = request.getHeader("Cookie");
        ResponseEntity<UserValidateResponseDto> authResponse = authServerClient.validateUser(cookieHeader);

        //calling the product server's purchase endpoint to check with products db
        ResponseEntity<List<ProductPurchaseResponseDto>> productResponse = productServerClient.purchaseProducts(orderRequestDto.orderItems());

        //getting the total price from the items store in db
        BigDecimal totalAmount = BigDecimal.ZERO;
        assert productResponse.getBody() != null;
        for(ProductPurchaseResponseDto product : productResponse.getBody()) {
            totalAmount = totalAmount.add(product.price());
        }

        //saving the order to order database
        assert authResponse.getBody() != null;
        OrderModel order = orderRepository.save(orderMapper.mapToOrderModel(orderRequestDto, authResponse.getBody().userId(), totalAmount));

        //payment

        //notification

        return null;
    }
}
