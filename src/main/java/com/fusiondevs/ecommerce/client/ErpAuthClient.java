package com.fusiondevs.ecommerce.client;

import com.fusiondevs.ecommerce.dto.session.AuthenticationRequest;
import com.fusiondevs.ecommerce.dto.session.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="erpAuthClient", url="${erp.base.url}")
public interface ErpAuthClient {
    //login
    @PostMapping(value = "/authenticate", consumes = "application/json")
    ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request);

    //get username /me
    @GetMapping(value = "/auth/user")
    String getUserName();

    //logout
    @PostMapping(value = "/logout")
    ResponseEntity<?> logout();

    //register
    @PostMapping(value = "/register/user")
    ResponseEntity<?> register(@RequestBody RegisterRequest request);
}
