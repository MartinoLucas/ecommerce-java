package com.fusiondevs.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ApiResponse", description = "Response object")
public class ApiResponse<T> {
    @Schema(name = "code", description = "Response code", example = "200")
    private int code;
    @Schema(name = "message", description = "Response message", example = "Success")
    private String message;
    @Schema(name = "body", description = "Response body", example = "null")
    private T body;
}
