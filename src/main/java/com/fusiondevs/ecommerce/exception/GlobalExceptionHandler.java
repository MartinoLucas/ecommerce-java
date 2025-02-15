package com.fusiondevs.ecommerce.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusiondevs.ecommerce.dto.ApiResponse;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(NotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreateException.class)
    public ResponseEntity<ApiResponse<Object>> handleCreateException(CreateException ex) {
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignException(FeignException ex) {
        int status = ex.status();
        String body = "Global Handler says: Feign Exception Caught: " + ex.getMessage();

        String errorContent = ex.contentUTF8();
        String extractedMessage = "";
        try {
            // Intentamos parsear el error como un objeto JSON
            Map<String, Object> errorMap = mapper.readValue(errorContent, new TypeReference<Map<String, Object>>() {});
            if (errorMap.containsKey("message")) {
                extractedMessage = (String) errorMap.get("message");
            } else {
                extractedMessage = errorContent;
            }
        } catch (Exception e) {
            // Si falla el parseo, se usa el contenido original o una cadena vacía
            extractedMessage = errorContent;
        }

        ApiResponse<Object> response = new ApiResponse<>(status, extractedMessage, body);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.valueOf(status));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex) {
        // Puedes agregar lógica para mapear diferentes tipos de excepción a distintos códigos HTTP
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = "Global Handler: " + ex.getMessage();

        ApiResponse<Object> response = new ApiResponse<>(status, message, null);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
