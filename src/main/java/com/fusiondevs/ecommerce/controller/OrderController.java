package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.order.OrderItemRequest;
import com.fusiondevs.ecommerce.dto.order.OrderResponse;
import com.fusiondevs.ecommerce.exception.CreateException;
import com.fusiondevs.ecommerce.service.OrderService;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderResponse> createOrder() throws CreateException {
        try {
            return ResponseEntity.ok(orderService.createOrder());
        } catch (CreateException e) {
            throw new CreateException("Error creating order");
        } catch (FeignException e) {
            throw new CreateException(e.getMessage());
        }
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.POST)
    public ResponseEntity<OrderResponse> addItemToOrder(@PathVariable String orderId, @RequestBody OrderItemRequest orderItemRequest) throws CreateException {
        try {
            return ResponseEntity.ok(orderService.addItemToOrder(orderId, orderItemRequest));
        } catch (CreateException e) {
            throw new CreateException("Error adding item to order");
        } catch (FeignException e) {
            throw new CreateException(e.getMessage());
        }
    }
}
