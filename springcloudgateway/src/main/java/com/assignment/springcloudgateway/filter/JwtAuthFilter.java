package com.assignment.springcloudgateway.filter;


import com.assignment.springcloudgateway.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

    @Component
    public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

        @Autowired
        private RouteValidator validator;

        @Autowired
        private RestTemplate template;
        @Autowired
        private JwtUtil jwtUtil;

        public JwtAuthFilter() {
            super(Config.class);
        }

        @Override
        public GatewayFilter apply(Config config) {
            return ((exchange, chain) -> {
                if (validator.isSecured.test(exchange.getRequest())) {

                    if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                        throw new RuntimeException("Invalid authorization header");
                    }

                    String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        authHeader = authHeader.substring(7);
                    }
                    try {
                        jwtUtil.validateToken(authHeader);
                    } catch (Exception e) {
                        throw new RuntimeException("");
                    }
                }
                return chain.filter(exchange);
            });
        }


        public static class Config {

        }
    }