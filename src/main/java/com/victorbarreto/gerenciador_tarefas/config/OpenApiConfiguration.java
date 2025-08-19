package com.victorbarreto.gerenciador_tarefas.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Gerenciador Tarefas API",
        version = "v1",
        contact = @Contact(
            name = "Victor Barreto"
        )
    )
)
public class OpenApiConfiguration {
}
