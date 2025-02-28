package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.session.AuthenticationRequest;
import com.fusiondevs.ecommerce.dto.session.AuthenticationResult;
import com.fusiondevs.ecommerce.dto.session.RegisterRequest;
import com.fusiondevs.ecommerce.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    //@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @PostMapping("/login")
    @Operation(summary = "Login a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logged in successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.fusiondevs.ecommerce.dto.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de éxito",
                                    value = """
                    {
                      "code": 200,
                      "message": "User logged in successfully",
                      "body": {
                        "token": "string",
                        "cookie": "string"
                      }
                    }
                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.fusiondevs.ecommerce.dto.ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de error 401",
                                    value = """
                    {
                      "code": 401,
                      "message": "User not authorized",
                      "body": null
                    }
                    """
                            )
                    )
            )
    })
    public ResponseEntity<AuthenticationResult> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResult result = authenticationService.authenticate(request);

        HttpHeaders headers = new HttpHeaders();
        if (result.getCookie() != null) {
            headers.add(HttpHeaders.SET_COOKIE, result.getCookie());
        }

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    //@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    @PostMapping("/logout")
    @Operation(summary = "Logout the current user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged out successfully"),
            @ApiResponse(responseCode = "401", description = "User not authorized")
    })
    public ResponseEntity<?> logout(HttpServletResponse response) {
        authenticationService.logout(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "401", description = "User not authorized")
    })
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authenticationService.register(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@RequestMapping(value = {"/me"}, method = RequestMethod.GET)
    @GetMapping("/me")
    @Operation(summary = "Get the current user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "User not authorized")
    })
    public ResponseEntity<String> me() {
        String username = authenticationService.getUserName();
        return ResponseEntity.ok(username);
    }
}
