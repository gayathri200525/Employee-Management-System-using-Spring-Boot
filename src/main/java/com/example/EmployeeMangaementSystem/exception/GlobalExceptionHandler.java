package com.example.EmployeeMangaementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<String> invalidOtp(InvalidOtpException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<String> expiredOtp(OtpExpiredException ex) {
        return ResponseEntity.status(HttpStatus.GONE)
                .body(ex.getMessage());
    }

    @ExceptionHandler(OtpNotFoundException.class)
    public ResponseEntity<String> otpNotFound(OtpNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}