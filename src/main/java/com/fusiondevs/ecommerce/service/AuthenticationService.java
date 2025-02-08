package com.fusiondevs.ecommerce.service;

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
    private RestTemplate restTemplate;

    // La URL base del ERP se configura según el entorno (development: http://localhost:8081, production: https://admin.cuartageneracionvinos.com:3000)
    @Value("${erp.base.url}")
    private String erpBaseUrl;

    public AuthenticationResult authenticate(AuthenticationRequest request) {
        // Construye la URL del endpoint de autenticación del ERP
        String url = erpBaseUrl + "/authenticate";

        // Configura los headers de la petición
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthenticationRequest> entity = new HttpEntity<>(request, headers);

        // Realiza la llamada al ERP (por ejemplo, usando POST)
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);

        // Obtiene el token (se espera que sea un String) del body de la respuesta
        String token = responseEntity.getBody();

        // Extrae la cookie del header "Set-Cookie" (si existe)
        List<String> cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
        String cookie = (cookies != null && !cookies.isEmpty()) ? cookies.get(0) : null;

        return new AuthenticationResult(token, cookie);
    }
}
