package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.dto.AuthenticationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="erpAuthClient", url="${erp.base.url}")
public interface ErpAuthClient {
    @PostMapping(value = "/authenticate", consumes = "application/json")
    ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request);
}
