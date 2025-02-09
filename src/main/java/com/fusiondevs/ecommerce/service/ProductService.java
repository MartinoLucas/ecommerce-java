package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpProductClient;
import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ErpProductClient erpProductClient;

    public ProductService(ErpProductClient erpProductClient) {
        this.erpProductClient = erpProductClient;
    }

    public List<ProductDTO> getAllProducts() {
        return erpProductClient.getAllProducts();
    }

}
