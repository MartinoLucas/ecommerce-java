package com.fusiondevs.ecommerce.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    Long id;
    LocalDateTime orderDate;
    Double totalAmount;
    Double discount;
    String status;
    Double netAmount;
    List<OrderItemResponse> orderItems=new ArrayList<OrderItemResponse>();
}
