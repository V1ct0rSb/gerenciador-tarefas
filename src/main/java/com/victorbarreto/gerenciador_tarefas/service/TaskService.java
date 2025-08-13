package com.victorbarreto.gerenciador_tarefas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.victorbarreto.gerenciador_tarefas.dto.TaskCreateDTO;
import com.victorbarreto.gerenciador_tarefas.dto.TaskStatusDTO;
import com.victorbarreto.gerenciador_tarefas.entity.TaskModel;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;
import com.victorbarreto.gerenciador_tarefas.repository.TaskRepository;
import com.victorbarreto.gerenciador_tarefas.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import static com.victorbarreto.gerenciador_tarefas.entity.TaskStatus.PENDING;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskModel createTask(TaskCreateDTO taskCreateDTO, String username) {
        UserModel userModel = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        TaskModel taskModel = new TaskModel();

        taskModel.setTitle(taskCreateDTO.title());
        taskModel.setDescription(taskCreateDTO.description());
        taskModel.setStatus(PENDING);

        taskModel.setUser(userModel);
        userModel.setTasks(taskModel.getUser().getTasks());

        return taskRepository.save(taskModel);
    }

    public List<TaskModel> seeTaskUsuario(String username) {
        UserModel userModel = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(userModel);
    }

    public List<TaskModel> seeTaskAdm(String username) {
        UserModel userModel = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findAll();
    }

    public TaskModel statusUpdate(Long id, String username, TaskStatusDTO taskStatusDTO) {
        TaskModel taskModel = taskRepository.findByIdAndUser_Username(id, username)
            .orElseThrow(() -> new RuntimeException("Task not found or does not belong to the user!"));

        taskModel.setStatus(taskStatusDTO.status());

        return taskRepository.save(taskModel);
    }
}
