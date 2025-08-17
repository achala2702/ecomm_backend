package com.achala.order_server.config;

import com.achala.order_server.dto.OrderConfirmationDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.hibernate.query.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name("order-topic")
                .build();
    }

//    @Bean
//    public ProducerFactory<String, OrderConfirmationDto> producerFactory() {
//
//    }
//
//    @Bean
//    public KafkaTemplate<String, OrderConfirmationDto> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
}
