package com.tarsem.urlforge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "URLForge API",
                version = "1.0.0",
                description = "Production-ready URL Shortening Service built using Spring Boot 3 and PostgreSQL.",
                contact = @Contact(
                        name = "Tarsem Gulab",
                        email = "work4tarsemgulab@gmail.com"
                )
        )
)
public class OpenApiConfig {
}