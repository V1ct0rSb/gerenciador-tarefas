package com.victorbarreto.gerenciador_tarefas.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.victorbarreto.gerenciador_tarefas.entity.TaskStatus;

public record TaskResponseDTO(UUID id, String username, String title, String description, TaskStatus status,
                              LocalDateTime createdAt) {
}
