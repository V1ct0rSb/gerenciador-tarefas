package com.victorbarreto.gerenciador_tarefas.dto;

import com.victorbarreto.gerenciador_tarefas.entity.Roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDTO(
    @NotBlank(message = "Username is required") String username,
    @NotBlank(message = "Password is required") String password,
    @NotNull(message = "Enum is required") Roles role
) {
}
