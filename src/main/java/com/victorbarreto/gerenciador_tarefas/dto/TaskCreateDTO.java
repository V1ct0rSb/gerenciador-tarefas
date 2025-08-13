package com.victorbarreto.gerenciador_tarefas.dto;

import com.victorbarreto.gerenciador_tarefas.entity.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskCreateDTO(@NotBlank(message = "Title is required") String title,
                            @NotBlank(message = "Description is required") String description) {
}
