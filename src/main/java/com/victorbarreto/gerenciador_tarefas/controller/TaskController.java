package com.victorbarreto.gerenciador_tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.victorbarreto.gerenciador_tarefas.dto.TaskCreateDTO;
import com.victorbarreto.gerenciador_tarefas.entity.TaskModel;
import com.victorbarreto.gerenciador_tarefas.service.TaskService;
import com.victorbarreto.gerenciador_tarefas.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/tasks")
    public ResponseEntity<TaskModel> criarTask(
        @Valid @RequestBody TaskCreateDTO taskCreateDTO,
        Authentication authentication) {

        String username = authentication.getName();
        TaskModel taskModel = taskService.createTask(taskCreateDTO, username);

        return ResponseEntity.status(201).body(taskModel);
    }

}
