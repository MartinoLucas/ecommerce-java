package com.fusiondevs.ecommerce.service.session;

import com.fusiondevs.ecommerce.client.ErpAuthClient;
import com.fusiondevs.ecommerce.dto.session.AuthenticationRequest;
import com.fusiondevs.ecommerce.dto.session.AuthenticationResult;
import com.fusiondevs.ecommerce.session.UserSessionToken;
import com.fusiondevs.ecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private ErpAuthClient erpAuthClient;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserSessionToken userSessionToken;

    public AuthenticationResult authenticate(AuthenticationRequest request) {
        // Llama al endpoint /authenticate del ERP a través del cliente Feign
        ResponseEntity<String> responseEntity = erpAuthClient.authenticate(request);

        // Obtiene el token (se espera que sea un String) del body de la respuesta
        String token = responseEntity.getBody();

        userSessionToken.setToken(token);

        // Extrae la cookie del header "Set-Cookie" (si existe)
        List<String> cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
        String cookie = (cookies != null && !cookies.isEmpty()) ? cookies.get(0) : null;

        return new AuthenticationResult(token, cookie);
    }

    public String getUserName(String token) {
        return jwtUtil.extractUsername(token);
    }
}
