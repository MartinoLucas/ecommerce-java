package com.fusiondevs.ecommerce.dto.category;

import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryResponse {
    private Long id;
    private String name;
    private String description;
    private CategoryResponse category;
    private List<ProductDTO> products;
}
