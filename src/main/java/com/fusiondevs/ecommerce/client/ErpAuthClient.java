package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.dto.session.AuthenticationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="erpAuthClient", url="${erp.base.url}")
public interface ErpAuthClient {
    @PostMapping(value = "/authenticate", consumes = "application/json")
    ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request);

    @GetMapping(value = "/auth/user")
    String getUserName();

    @PostMapping(value = "/logout")
    ResponseEntity<?> logout();
}
