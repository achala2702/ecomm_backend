package com.achala.order_server.dto;

import com.achala.order_server.util.HttpStatusCodeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDto(
        @JsonDeserialize(using = HttpStatusCodeDeserializer.class)
        HttpStatusCode status,
        LocalDateTime timeStamp,
        Object errors
) {
}
