package com.example.batis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .description("Test task for TELDA")
                                .version("1.0")
                                .title("Regions RESTful API")
                                .contact(new Contact()
                                        .name("Elizaveta Moskalenko")
                                        .email("moonblack556@gmail.com")
                                )
                );
    }
}
