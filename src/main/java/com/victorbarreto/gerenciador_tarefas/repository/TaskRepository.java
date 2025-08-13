package com.victorbarreto.gerenciador_tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.victorbarreto.gerenciador_tarefas.entity.TaskModel;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByUser(UserModel userModel);
}
