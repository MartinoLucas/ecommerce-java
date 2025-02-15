package com.fusiondevs.ecommerce.dto.product;

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
    private String categoryName;
    private Long categoryId;
    private String subCategoryName;
    private Long subCategoryId;
    private Double price;
    private Double cost;
    private Integer stock;
    private String imageUrl;

}
