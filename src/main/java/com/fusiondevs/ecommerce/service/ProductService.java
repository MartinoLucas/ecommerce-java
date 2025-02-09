package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpProductClient;
import com.fusiondevs.ecommerce.dto.ProductDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ErpProductClient erpProductClient;

    public ProductService(ErpProductClient erpProductClient) {
        this.erpProductClient = erpProductClient;
    }

    // Se define un bulkhead para limitar las llamadas concurrentes al ERP.
    // En este ejemplo, si se excede el límite, se invoca el método fallback.
    @Bulkhead(name = "productBulkhead", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "fallbackGetAllProducts")
    public List<ProductDTO> getAllProducts() {
        return erpProductClient.getAllProducts();
    }

    // Método de respaldo en caso de que se exceda la concurrencia permitida
    public List<ProductDTO> fallbackGetAllProducts(Throwable t) {
        // Aquí podrías retornar datos en caché, un mensaje alternativo o una lista vacía
        return List.of();
    }
}
