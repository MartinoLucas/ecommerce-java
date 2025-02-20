package com.fusiondevs.ecommerce.controller;

import com.fusiondevs.ecommerce.dto.session.AuthenticationRequest;
import com.fusiondevs.ecommerce.dto.session.AuthenticationResult;
import com.fusiondevs.ecommerce.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResult> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResult result = authenticationService.authenticate(request);

        HttpHeaders headers = new HttpHeaders();
        if (result.getCookie() != null) {
            headers.add(HttpHeaders.SET_COOKIE, result.getCookie());
        }

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public ResponseEntity<?> logout(HttpServletResponse response) {
        authenticationService.logout(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    /*@RequestMapping(value = {"/me"}, method = RequestMethod.GET)
    public ResponseEntity<?> me() {
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
