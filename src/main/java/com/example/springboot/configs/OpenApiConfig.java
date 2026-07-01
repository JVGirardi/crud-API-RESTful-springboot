package com.example.springboot.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RESTful de Produtos - CRUD")
                        .version("v1")
                        .description("API RESTful para gerenciamento de produtos, construída com Spring Boot 4.1.")
                        .contact(new Contact()
                                .name("João Girardi")
                                .email("joaovgirardi@gmail.com")));
    }
}
