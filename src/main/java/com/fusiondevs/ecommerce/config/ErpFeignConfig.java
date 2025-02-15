package com.fusiondevs.ecommerce.config;

import com.fusiondevs.ecommerce.session.UserSessionToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErpFeignConfig {
    @Autowired
    private UserSessionToken userSessionToken;

    @Bean
    public RequestInterceptor erpRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String token = userSessionToken.getToken();
                if (token != null && !token.isBlank()) {
                    // Añadimos la cabecera: Authorization: Bearer <token>
                    requestTemplate.header("Authorization", "Bearer " + token);
                }
            }
        };
    }
}
