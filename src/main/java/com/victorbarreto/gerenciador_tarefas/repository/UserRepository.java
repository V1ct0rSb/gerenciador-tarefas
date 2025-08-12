package com.victorbarreto.gerenciador_tarefas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    boolean existsByUsername(String username);
}
