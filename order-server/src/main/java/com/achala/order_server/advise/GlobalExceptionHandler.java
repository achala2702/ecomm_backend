package com.achala.order_server.advise;

import com.achala.order_server.dto.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //handle FeignException
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponseDto> handleFeignException(FeignException e) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        ErrorResponseDto errorResponse;

        try {
            errorResponse = mapper.readValue(e.contentUTF8(), ErrorResponseDto.class);
        } catch (Exception ex) {
            // fallback if parsing fails
            errorResponse = ErrorResponseDto.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .errors(ex.getMessage())
                    .timeStamp(LocalDateTime.now())
                    .build();
        }
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

    //handle all unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnhandledExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .timeStamp(LocalDateTime.now())
                        .errors(e.getMessage())
                        .build());
    }

}
