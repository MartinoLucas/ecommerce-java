package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpProductClient;
import com.fusiondevs.ecommerce.dto.product.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ErpProductClient erpProductClient;

    public ProductService(ErpProductClient erpProductClient) {
        this.erpProductClient = erpProductClient;
    }

    public Map<String, Object> getAllProducts(String search,
                                              Long categoryId,
                                              Long subCategoryId,
                                              Double minPrice,
                                              Double maxPrice,
                                              String sort,
                                              int page,
                                              int size) {

        List<ProductDTO> products = erpProductClient.getAllProducts();

        //Filtrado
        List<ProductDTO> filteredProducts = products.stream()
                .filter(p -> (search == null || p.getName().toLowerCase().contains(search.toLowerCase()) ||
                        p.getDescription().toLowerCase().contains(search.toLowerCase())))
                .filter(p -> (categoryId == null || p.getCategoryId().equals(categoryId)))
                .filter(p -> (subCategoryId == null || p.getSubCategoryId().equals(subCategoryId)))
                .filter(p -> (minPrice == null || p.getPrice() >= minPrice))
                .filter(p -> (maxPrice == null || p.getPrice() <= maxPrice))
                .sorted((p1, p2) -> sort.equalsIgnoreCase("asc") ?
                        Double.compare(p1.getPrice(), p2.getPrice()) :
                        Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());

        // Paginación manual
        int start = page * size;
        int end = Math.min(start + size, filteredProducts.size());

        List<ProductDTO> pagedProducts = (start < filteredProducts.size()) ?
                filteredProducts.subList(start, end) : new ArrayList<>();

        // Creando la respuesta con metadata de paginación
        Map<String, Object> response = new HashMap<>();
        response.put("products", pagedProducts);
        response.put("totalElements", filteredProducts.size());
        response.put("totalPages", (int) Math.ceil((double) filteredProducts.size() / size));
        response.put("currentPage", page);
        response.put("pageSize", size);

        return response;
    }

    public ProductDTO getProduct(Long id) {
        return erpProductClient.getProduct(id);
    }

    /*public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return erpProductClient.getProductsByCategory(categoryId);
    }

    public List<ProductDTO> getProductsBySubCategory(Long subCategoryId) {
        return erpProductClient.getProductsBySubCategory(subCategoryId);
    }

    public List<ProductDTO> getProductsByName(String name) {
        return erpProductClient.getProductsByName(name);
    }

    public List<ProductDTO> filterProducts(ProductFilterRequest filterRequest) {
        return erpProductClient.filterProducts(filterRequest);
    }*/

}
