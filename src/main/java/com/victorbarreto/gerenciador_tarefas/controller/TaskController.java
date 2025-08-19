package com.victorbarreto.gerenciador_tarefas.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.victorbarreto.gerenciador_tarefas.dto.TaskCreateDTO;
import com.victorbarreto.gerenciador_tarefas.dto.TaskResponseDTO;
import com.victorbarreto.gerenciador_tarefas.dto.TaskStatusDTO;
import com.victorbarreto.gerenciador_tarefas.entity.TaskModel;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;
import com.victorbarreto.gerenciador_tarefas.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/tasks")
    public ResponseEntity<TaskModel> criarTask(
        @Valid @RequestBody TaskCreateDTO taskCreateDTO,
        Authentication authentication) {

        String username = authentication.getName();
        TaskModel taskModel = taskService.createTask(taskCreateDTO, username);

        return ResponseEntity.status(201).body(taskModel);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskModel>> seeTaskUsuario(Authentication authentication) {
        String username = authentication.getName();
        List<TaskModel> taskModelList = taskService.seeTaskUsuario(username);
        return ResponseEntity.ok().body(taskModelList);
    }

    // Listar todas tasks
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tasks/admin")
    public ResponseEntity<List<TaskResponseDTO>> seeTaskAdm(Authentication authentication) {
        String username = authentication.getName();
        List<TaskResponseDTO> taskModelList = taskService.seeTaskAdm(username);
        return ResponseEntity.ok().body(taskModelList);
    }


    @PreAuthorize("hasRole('USER')")
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskModel> statusUpdate(@PathVariable UUID id,
                                                  @RequestBody TaskStatusDTO taskStatusDTO,
                                                  Authentication authentication) {
        String username = authentication.getName();
        TaskModel taskModel = taskService.statusUpdate(id, username, taskStatusDTO);
        return ResponseEntity.ok().body(taskModel);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deletarTask(@PathVariable UUID id, Authentication authentication) {
        String username = authentication.getName();
        taskService.deletarTask(id, username);

        return ResponseEntity.noContent().build();
    }

}
