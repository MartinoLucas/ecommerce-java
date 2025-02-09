package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.AuthenticationRequest;
import com.fusiondevs.ecommerce.dto.AuthenticationResult;
import com.fusiondevs.ecommerce.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResult> authenticate(@RequestBody AuthenticationRequest request) {
        // Llama al servicio que hace el proxy al ERP
        AuthenticationResult result = authenticationService.authenticate(request);

        // Prepara los headers para reenviar la cookie (si la recibió del ERP)
        HttpHeaders headers = new HttpHeaders();
        if (result.getCookie() != null) {
            headers.add(HttpHeaders.SET_COOKIE, result.getCookie());
        }

        // Se retorna el resultado con status OK (200). La respuesta se envolverá automáticamente en el ApiResponse.
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
}
