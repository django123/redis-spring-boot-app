package com.django.redis_spring_boot_app.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "EDOUGA JEAN PATRICK",
                        email = "edougajean@gmail.com"
                ),
                description = "OpenApi documentation for redis spring boot app",
                title = "OpenApi specification - EDOUGA",
                version ="1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local Dev",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Prod Dev",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
