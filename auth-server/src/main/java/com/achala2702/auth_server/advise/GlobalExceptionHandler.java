package com.achala2702.auth_server.advise;

import com.achala2702.auth_server.dto.ErrorResponseDto;
import com.achala2702.auth_server.exception.UserAlreadyExistsException;
import com.achala2702.auth_server.exception.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
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

    //handle user-already-exists exception
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponseDto.builder()
                        .status(HttpStatus.CONFLICT)
                        .timeStamp(LocalDateTime.now())
                        .errors(e.getMessage())
                        .build());
    }

    //handle signature exception jwt
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponseDto> handleSignatureException(SignatureException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDto.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .timeStamp(LocalDateTime.now())
                        .errors(e.getMessage())
                        .build());
    }

    //handle ExpiredJwt exception
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDto> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDto.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .timeStamp(LocalDateTime.now())
                        .errors(e.getMessage())
                        .build());
    }

    //exception handler for all the unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnhandledException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .timeStamp(LocalDateTime.now())
                        .errors(e.getMessage())
                        .build());
    }
}
