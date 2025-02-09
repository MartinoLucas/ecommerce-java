package com.fusiondevs.ecommerce.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusiondevs.ecommerce.dto.ApiResponse;

public class ApiResponseUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(int code, String message, Object body) throws JsonProcessingException {
        ApiResponse<Object> response = new ApiResponse<>(code, message, body);
        return mapper.writeValueAsString(response);
    }
}
