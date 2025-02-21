package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.dto.category.CategoryResponse;
import com.fusiondevs.ecommerce.dto.category.SubCategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="erpClient", url="${erp.base.url}")
public interface ErpClient {

    @GetMapping(value="/api/category/getAll")
    List<CategoryResponse> getAllCategories();

    @GetMapping(value="/api/subcategory/getAll")
    List<SubCategoryResponse> getAllSubCategories();
}
