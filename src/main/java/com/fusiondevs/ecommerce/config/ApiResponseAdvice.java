package com.fusiondevs.ecommerce.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusiondevs.ecommerce.dto.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        String packageName = returnType.getContainingClass().getPackageName();
        if(packageName.startsWith("org.springdoc") || packageName.startsWith("io.swagger")) {
            return false;
        }
        // O también puedes chequear la URL si accedes al request a través de RequestContextHolder,
        // pero filtrar por paquete es lo más sencillo.
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // Si el body ya es un ApiResponse, se retorna sin modificar
        if (body instanceof ApiResponse) {
            return body;
        }

        // Si la respuesta es un ResponseEntity, se desempaqueta para extraer status, headers y body
        if (body instanceof ResponseEntity) {
            ResponseEntity<?> entity = (ResponseEntity<?>) body;
            Object innerBody = entity.getBody();
            int status = entity.getStatusCodeValue();
            String reason = HttpStatus.resolve(status) != null ? HttpStatus.resolve(status).getReasonPhrase() : "";
            ApiResponse<Object> apiResponse = new ApiResponse<>(status, reason, innerBody);

            // Copiamos los headers existentes y establecemos content-type a application/json
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(entity.getHeaders());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<ApiResponse<Object>> newEntity =
                    new ResponseEntity<>(apiResponse, headers, entity.getStatusCode());

            // Si el método declarado espera un String, devolvemos el JSON serializado
            if (String.class.equals(returnType.getParameterType())) {
                try {
                    return mapper.writeValueAsString(newEntity.getBody());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return newEntity;
        }

        // Para cualquier otro tipo de respuesta, se toma el status del response (si se puede obtener)
        int status = 200;
        if (response instanceof ServletServerHttpResponse) {
            status = ((ServletServerHttpResponse) response).getServletResponse().getStatus();
        }
        String reason = HttpStatus.resolve(status) != null ? HttpStatus.resolve(status).getReasonPhrase() : "";
        ApiResponse<Object> apiResponse = new ApiResponse<>(status, reason, body);
        return apiResponse;
    }
}
