package com.assignment.authenticationservice.auth;


import com.assignment.authenticationservice.config.JwtService;
import com.assignment.authenticationservice.user.User;
import com.assignment.authenticationservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        var userExist = userRepository.findByUsername(request.getUsername());

        if (!userExist.isEmpty()) {
            return null;
        }

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user.getUsername());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)

                .build();
    }
    public String  authenticate(AuthenticationRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user.getUsername());

        return jwtToken;
    }




}
