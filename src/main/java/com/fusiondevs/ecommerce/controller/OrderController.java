package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.order.OrderItemRequest;
import com.fusiondevs.ecommerce.dto.order.OrderResponse;
import com.fusiondevs.ecommerce.exception.CreateException;
import com.fusiondevs.ecommerce.exception.NotFoundException;
import com.fusiondevs.ecommerce.service.OrderService;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Tag(name = "Order", description = "Order API")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @Operation(summary = "Create a new order")
    @ApiResponse(responseCode = "200", description = "Order created successfully")
    public ResponseEntity<OrderResponse> createOrder() throws CreateException {
        try {
            return ResponseEntity.ok(orderService.createOrder());
        } catch (CreateException e) {
            throw new CreateException("Error creating order");
        } catch (FeignException e) {
            throw new CreateException(e.getMessage());
        }
    }

    //@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    @GetMapping("/{orderId}")
    @Operation(summary = "Get an order by ID")
    @ApiResponse(responseCode = "200", description = "Order retrieved successfully")
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

    //@RequestMapping(value = "/{orderId}/items", method = RequestMethod.POST)
    @PostMapping("/{orderId}/items")
    @Operation(summary = "Add an item to an order")
    @ApiResponse(responseCode = "200", description = "Item added to order successfully")
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
