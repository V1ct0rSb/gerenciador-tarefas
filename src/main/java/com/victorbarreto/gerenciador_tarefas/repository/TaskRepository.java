package com.victorbarreto.gerenciador_tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.victorbarreto.gerenciador_tarefas.entity.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
}
