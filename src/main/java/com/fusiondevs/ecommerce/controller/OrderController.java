package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.order.OrderItemRequest;
import com.fusiondevs.ecommerce.dto.order.OrderResponse;
import com.fusiondevs.ecommerce.exception.CreateException;
import com.fusiondevs.ecommerce.exception.NotFoundException;
import com.fusiondevs.ecommerce.service.OrderService;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
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

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String orderId) throws NotFoundException {
        try {
            return ResponseEntity.ok(orderService.getOrder(orderId));
        } catch (NotFoundException e) {
            throw new NotFoundException("Error getting order");
        } catch (FeignException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public List<OrderResponse> getAllOrders(){
//        try {
//            return orderService.getAllOrders();
//        } catch (FeignException e) {
//            throw e;
//        }
//    }

    @RequestMapping(value = "/{orderId}/items", method = RequestMethod.POST)
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
