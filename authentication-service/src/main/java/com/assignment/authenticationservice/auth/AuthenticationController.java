package com.assignment.authenticationservice.auth;


import com.assignment.authenticationservice.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/auth")
    @RequiredArgsConstructor
    public class AuthenticationController {
        @Autowired
        private AuthenticationService service;

        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
            return ResponseEntity.ok(service.register(request));
        }

        @PostMapping("/authenticate")
        public String authenticate(@RequestBody AuthenticationRequest request) {
            return service.authenticate(request);
        }

        }

