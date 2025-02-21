package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.category.SubCategoryResponse;
import com.fusiondevs.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {
    private final CategoryService categoryService;

    public SubCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategories() {
        List<SubCategoryResponse> subCategories = categoryService.getAllSubCategories();
        return ResponseEntity.ok(subCategories);
    }
}
