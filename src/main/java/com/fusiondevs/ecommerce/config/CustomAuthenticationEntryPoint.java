package com.fusiondevs.ecommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusiondevs.ecommerce.dto.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Construye el ApiResponse con el mismo formato
        ApiResponse<Object> apiResponse = new ApiResponse<>(
                HttpStatus.UNAUTHORIZED.value(),
                "Security: Unauthorized access: you must be logged in ecommerce",
                null
        );

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        // Escribe el objeto ApiResponse como JSON en el response
        mapper.writeValue(response.getOutputStream(), apiResponse);
    }

}
