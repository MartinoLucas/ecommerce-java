package com.fusiondevs.ecommerce.dto.order;

import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    Long id;
    ProductDTO product;
    Integer quantity;
    Double price;
    Double productProfit;
    String productInfo;
    String productName;
}
