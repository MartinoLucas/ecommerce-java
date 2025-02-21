package com.fusiondevs.ecommerce.dto.category;

import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private List<?> subCategories;
    private List<ProductDTO> products;
}
