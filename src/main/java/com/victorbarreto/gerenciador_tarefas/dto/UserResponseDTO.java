package com.victorbarreto.gerenciador_tarefas.dto;

import java.util.UUID;

import com.victorbarreto.gerenciador_tarefas.entity.Roles;

public record UserResponseDTO(UUID id, String username, Roles role) {
}
