package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import com.fusiondevs.ecommerce.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/public/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint para obtener todos los productos
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long subCategory,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Map<String, Object> response = productService.getAllProducts(search, category, subCategory, minPrice, maxPrice, sort, page, size);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value={"/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        ProductDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    /*@GetMapping("category/{categoryId}")
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
    }*/
}
