package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="erpProductClient", url="${erp.base.url}/api/products")
public interface ErpProductClient {
    @GetMapping(value="/getAll")
    List<ProductDTO> getAllProducts();
}

