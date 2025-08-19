package com.victorbarreto.gerenciador_tarefas.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskCreateDTO(@NotBlank(message = "Title is required") String title,
                            @NotBlank(message = "Description is required") String description) {
}
