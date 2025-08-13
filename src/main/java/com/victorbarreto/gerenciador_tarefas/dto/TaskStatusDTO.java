package com.victorbarreto.gerenciador_tarefas.dto;

import com.victorbarreto.gerenciador_tarefas.entity.TaskStatus;

import jakarta.validation.constraints.NotNull;

public record TaskStatusDTO(@NotNull(message = "Enum is required") TaskStatus status) {
}
