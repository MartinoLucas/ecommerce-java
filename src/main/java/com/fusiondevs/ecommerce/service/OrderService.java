package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpOrderClient;
import com.fusiondevs.ecommerce.dto.order.OrderItemRequest;
import com.fusiondevs.ecommerce.dto.order.OrderRequest;
import com.fusiondevs.ecommerce.dto.order.OrderResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private final ErpOrderClient erpOrderClient;

    public OrderService(ErpOrderClient erpOrderClient) {
        this.erpOrderClient = erpOrderClient;
    }

    public OrderResponse createOrder() {
        OrderRequest orderRequest = new OrderRequest(LocalDateTime.now(), "PENDING");
        return erpOrderClient.createOrder(orderRequest);
    }

    public OrderResponse addItemToOrder(String orderId, OrderItemRequest orderItemRequest) {
        return erpOrderClient.addItemToOrder(orderId, orderItemRequest);
    }
}
