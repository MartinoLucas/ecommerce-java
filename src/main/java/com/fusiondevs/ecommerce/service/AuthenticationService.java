package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpAuthClient;
import com.fusiondevs.ecommerce.dto.AuthenticationRequest;
import com.fusiondevs.ecommerce.dto.AuthenticationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private ErpAuthClient erpAuthClient;

    public AuthenticationResult authenticate(AuthenticationRequest request) {
        // Llama al endpoint /authenticate del ERP a través del cliente Feign
        ResponseEntity<String> responseEntity = erpAuthClient.authenticate(request);

        // Obtiene el token (se espera que sea un String) del body de la respuesta
        String token = responseEntity.getBody();

        // Extrae la cookie del header "Set-Cookie" (si existe)
        List<String> cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
        String cookie = (cookies != null && !cookies.isEmpty()) ? cookies.get(0) : null;

        return new AuthenticationResult(token, cookie);
    }
}
