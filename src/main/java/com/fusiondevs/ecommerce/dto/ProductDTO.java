package com.fusiondevs.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Long subcategoryId;
    private Double cost;
    private Double price;
    private String imageUrl;

}
