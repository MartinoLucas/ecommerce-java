package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpProductClient;
import com.fusiondevs.ecommerce.dto.Page;
import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import com.fusiondevs.ecommerce.dto.product.ProductFilterRequest;
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

    public ProductDTO getProduct(Long id) {
        return erpProductClient.getProduct(id);
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return erpProductClient.getProductsByCategory(categoryId);
    }

    public List<ProductDTO> getProductsBySubCategory(Long subCategoryId) {
        return erpProductClient.getProductsBySubCategory(subCategoryId);
    }

    public List<ProductDTO> getProductsByPage(Page page) {
        return erpProductClient.getProductsByPage(page);
    }

    public List<ProductDTO> getProductsByName(String name) {
        return erpProductClient.getProductsByName(name);
    }

    public List<ProductDTO> filterProducts(ProductFilterRequest filterRequest) {
        return erpProductClient.filterProducts(filterRequest);
    }

}
