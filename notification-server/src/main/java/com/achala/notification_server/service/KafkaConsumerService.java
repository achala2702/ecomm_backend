package com.achala.notification_server.service;

import com.achala.notification_server.dto.OrderConfirmationDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmationDto orderConfirmationDto) {
        log.info("order confirmation received");
        System.out.println("message received" + orderConfirmationDto.toString());
    }
}
