package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.order.OrderResponse;
import com.fusiondevs.ecommerce.exception.CreateException;
import com.fusiondevs.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderResponse> createOrder() throws CreateException {
        return ResponseEntity.ok(orderService.createOrder());
        /*try {
            return ResponseEntity.ok(orderService.createOrder());
        } catch (Exception e) {
            throw new CreateException("Error creating order");
        }*/
    }
}
