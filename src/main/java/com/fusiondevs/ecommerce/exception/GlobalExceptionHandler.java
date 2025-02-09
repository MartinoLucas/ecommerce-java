package com.fusiondevs.ecommerce.exception;

import com.fusiondevs.ecommerce.dto.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex) {
        // Puedes agregar lógica para mapear diferentes tipos de excepción a distintos códigos HTTP
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = "Global Handler: " + ex.getMessage();

        ApiResponse<Object> response = new ApiResponse<>(status, message, null);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
