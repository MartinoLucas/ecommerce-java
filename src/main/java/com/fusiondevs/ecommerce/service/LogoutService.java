package com.fusiondevs.ecommerce.service;

import com.fusiondevs.ecommerce.client.ErpAuthClient;
import com.fusiondevs.ecommerce.dto.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {


    private final ErpAuthClient erpAuthClient;

    public LogoutService(AuthenticationService authenticationService, ErpAuthClient erpAuthClient) {
        this.erpAuthClient = erpAuthClient;
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
}
