package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import com.fusiondevs.ecommerce.dto.product.ProductFilterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="erpProductClient", url="${erp.base.url}/api/products")
public interface ErpProductClient {

    @GetMapping(value="/getAll")
    List<ProductDTO> getAllProducts();

    @GetMapping(value="/{id}")
    ProductDTO getProduct(Long id);

    @GetMapping(value="/category/{categoryId}")
    List<ProductDTO> getProductsByCategory(Long categoryId);

    @GetMapping(value="/subcategory/{subCategoryId}")
    List<ProductDTO> getProductsBySubCategory(Long subCategoryId);

    @GetMapping(value="/getByPage")
    List<ProductDTO> getProductsByPage(int page, int size);

    @GetMapping(value="/name")
    List<ProductDTO> getProductsByName(String name);

    @GetMapping(value="/filter")
    List<ProductDTO> filterProducts(ProductFilterRequest filterRequest);
}

