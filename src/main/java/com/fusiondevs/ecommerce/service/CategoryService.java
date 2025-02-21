package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpClient;
import com.fusiondevs.ecommerce.dto.category.CategoryResponse;
import com.fusiondevs.ecommerce.dto.category.SubCategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final ErpClient erpClient;

    public CategoryService(ErpClient erpClient) {
        this.erpClient = erpClient;
    }


    public List<CategoryResponse> getAllCategories() {
        return erpClient.getAllCategories();
    }

    public List<SubCategoryResponse> getAllSubCategories() {
        return erpClient.getAllSubCategories();
    }
}
