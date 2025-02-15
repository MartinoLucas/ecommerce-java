package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import com.fusiondevs.ecommerce.dto.product.ProductFilterRequest;
import com.fusiondevs.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/products/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint para obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        ProductDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("subcategory/{subCategoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsBySubCategory(@PathVariable Long subCategoryId) {
        List<ProductDTO> products = productService.getProductsBySubCategory(subCategoryId);
        return ResponseEntity.ok(products);
    }


    @GetMapping("name")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@RequestBody String name) {
        List<ProductDTO> products = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("filter")
    public ResponseEntity<List<ProductDTO>> filterProducts(@RequestBody ProductFilterRequest filterRequest) {
        List<ProductDTO> products = productService.filterProducts(filterRequest);
        return ResponseEntity.ok(products);
    }
}
