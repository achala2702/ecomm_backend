package com.achala.order_server.service;

import com.achala.order_server.client.AuthServerClient;
import com.achala.order_server.client.ProductServerClient;
import com.achala.order_server.dto.*;
import com.achala.order_server.model.OrderModel;
import com.achala.order_server.repository.OrderRepository;
import com.achala.order_server.util.OrderMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    private final KafkaProducerService kafkaProducerService;

    public OrderResponseDto CreateOrder(OrderRequestDto orderRequestDto, HttpServletRequest request) {

        //validating the user and get user information form auth server
        String cookieHeader = request.getHeader("Cookie");
        UserValidateResponseDto authResponse = authServerClient.validateUser(cookieHeader).getBody();

        //calling the product server's purchase endpoint to check with products db
        List<ProductPurchaseResponseDto> productResponse = productServerClient.purchaseProducts(orderRequestDto.orderItems()).getBody();

        //getting the total price from the items store in db
        BigDecimal totalAmount = BigDecimal.ZERO;
        assert productResponse != null;
        for(ProductPurchaseResponseDto product : productResponse) {
            totalAmount = totalAmount.add(product.price().multiply(BigDecimal.valueOf(product.quantity())));
        }

        //saving the order to order database
        assert authResponse != null;
        OrderModel order = orderRepository.save(orderMapper.mapToOrderModel(orderRequestDto, authResponse.userId(), totalAmount));

        //todo payment

        //sending confirmation msg to kafka
        OrderConfirmationDto orderConfirmationDto = orderMapper.mapToOrderConfirmationDto(authResponse, order, productResponse);
        kafkaProducerService.sendOrderConfirmation(orderConfirmationDto);

        return orderMapper.mapToOrderResponseDto(order, productResponse);
    }
}
