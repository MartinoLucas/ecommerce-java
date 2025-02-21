package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.dto.category.CategoryResponse;
import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import com.fusiondevs.ecommerce.dto.product.ProductFilterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="erpProductClient", url="${erp.base.url}/api/products/")
public interface ErpProductClient {

    @GetMapping(value="getAll")
    List<ProductDTO> getAllProducts();

    @GetMapping(value="{id}")
    ProductDTO getProduct(@PathVariable Long id);

}

