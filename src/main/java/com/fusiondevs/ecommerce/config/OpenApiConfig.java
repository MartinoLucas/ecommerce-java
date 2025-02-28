package com.fusiondevs.ecommerce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API para el E-Commerce | Cuarta Generacion Vinos")
                        .version("v1.0.0")
                        .description("Documentación detallada de la API para el ecommerce de la empresa comercializadora de vinos, de la ciudad de Junin, Buenos Aires, Cuarta Generacion.")
                        .license(new License().name("Licencia MIT").url("https://opensource.org/licenses/MIT"))
                );
    }



}

