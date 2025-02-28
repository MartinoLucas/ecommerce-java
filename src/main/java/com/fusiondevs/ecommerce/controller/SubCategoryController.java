package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.category.SubCategoryResponse;
import com.fusiondevs.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
@Tag(name = "SubCategory", description = "SubCategory API")
public class SubCategoryController {
    private final CategoryService categoryService;

    public SubCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    @Operation(summary = "Get all subcategories")
    @ApiResponse(responseCode = "200", description = "Subcategories retrieved successfully")
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategories() {
        List<SubCategoryResponse> subCategories = categoryService.getAllSubCategories();
        return ResponseEntity.ok(subCategories);
    }
}
