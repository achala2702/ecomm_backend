package com.achala.order_server.service;

import com.achala.order_server.dto.OrderConfirmationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderConfirmationDto> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmationDto orderConfirmationDto) {
        log.info("sending order confirmation");

        Message<OrderConfirmationDto> message = MessageBuilder.withPayload(orderConfirmationDto)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
