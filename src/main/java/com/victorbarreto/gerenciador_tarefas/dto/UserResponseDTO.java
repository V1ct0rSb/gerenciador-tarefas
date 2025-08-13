package com.victorbarreto.gerenciador_tarefas.dto;

import com.victorbarreto.gerenciador_tarefas.entity.Roles;

public record UserResponseDTO(Long id, String username, Roles role) {
}
