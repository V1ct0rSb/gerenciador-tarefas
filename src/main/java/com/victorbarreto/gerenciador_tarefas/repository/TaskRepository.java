package com.victorbarreto.gerenciador_tarefas.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.victorbarreto.gerenciador_tarefas.entity.TaskModel;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByUser(UserModel userModel);

    Optional<TaskModel> findByIdAndUser_Username(UUID id, String username);
}
