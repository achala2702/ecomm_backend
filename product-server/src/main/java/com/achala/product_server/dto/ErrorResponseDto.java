package com.achala.product_server.dto;

import lombok.Builder;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDto(
        HttpStatusCode status,
        LocalDateTime timeStamp,
        Object errors
) {
}
