package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.dto.session.AuthenticationRequest;
import com.fusiondevs.ecommerce.dto.session.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="erpAuthClient", url="${erp.base.url}/customer")
public interface ErpAuthClient {
    //login
    @PostMapping(value = "/login", consumes = "application/json")
    ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request);

    //get username /me
    @GetMapping(value = "/me")
    String getUserName();

    //logout
    @PostMapping(value = "/logout")
    ResponseEntity<?> logout();

    //register
    @PostMapping(value = "/register")
    ResponseEntity<?> register(@RequestBody RegisterRequest request);
}
