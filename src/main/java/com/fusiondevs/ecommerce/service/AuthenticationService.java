package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpAuthClient;
import com.fusiondevs.ecommerce.dto.ApiResponse;
import com.fusiondevs.ecommerce.dto.session.AuthenticationRequest;
import com.fusiondevs.ecommerce.dto.session.AuthenticationResult;
import com.fusiondevs.ecommerce.session.UserSessionToken;
import com.fusiondevs.ecommerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    public ApiResponse<?> logout(HttpServletResponse response) {
        System.out.println("Ecommerce: LogoutService.logout: calling erpAuthClient.logout");
        erpAuthClient.logout();
        System.out.println("Ecommerce: LogoutService.logout: called erpAuthClient.logout");

        System.out.println("Ecommerce: LogoutService.logout");
        Cookie jwtCookie = new Cookie("jwtToken", null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");

        response.addCookie(jwtCookie);

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), "Ecommerce: You have been logged out successfully", null);
        return apiResponse;
    }

    public String getUserName(String token) {
        return jwtUtil.extractUsername(token);
    }
}
