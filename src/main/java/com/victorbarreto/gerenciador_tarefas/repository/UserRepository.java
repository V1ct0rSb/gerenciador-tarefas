package com.victorbarreto.gerenciador_tarefas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByUsername(String username);

    boolean existsByUsername(String username);
}
