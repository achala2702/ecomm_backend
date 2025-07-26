package com.achala2702.auth_server.advise;

import com.achala2702.auth_server.dto.ErrorResponseDto;
import com.achala2702.auth_server.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //handle user-not-found exception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDto.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .timeStamp(LocalDateTime.now())
                        .errors(e.getMessage())
                        .build());
    }
}
