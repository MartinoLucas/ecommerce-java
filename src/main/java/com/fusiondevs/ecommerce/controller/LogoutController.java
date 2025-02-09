package com.fusiondevs.ecommerce.controller;


import com.fusiondevs.ecommerce.dto.ApiResponse;
import com.fusiondevs.ecommerce.service.LogoutService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    private final LogoutService logoutService;

    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @PostMapping
    public ResponseEntity<?> logout(HttpServletResponse response) {
        System.out.println("Ecommerce: LogoutController.logout");
        return ResponseEntity.ok(logoutService.logout(response));
    }
}
