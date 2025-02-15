package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.config.ErpFeignConfig;
import com.fusiondevs.ecommerce.dto.order.OrderItemRequest;
import com.fusiondevs.ecommerce.dto.order.OrderRequest;
import com.fusiondevs.ecommerce.dto.order.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="erpOrderClient", url="${erp.base.url}"+"/orders", configuration = ErpFeignConfig.class)
public interface ErpOrderClient {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    OrderResponse createOrder(OrderRequest orderRequest);

    @RequestMapping(value = "/{orderId}/items", method = RequestMethod.POST)
    OrderResponse addItemToOrder(@PathVariable String orderId, @RequestBody OrderItemRequest orderItemRequest);
}
