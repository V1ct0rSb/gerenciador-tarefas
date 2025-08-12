package com.victorbarreto.gerenciador_tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.victorbarreto.gerenciador_tarefas.config.SecurityConfig;
import com.victorbarreto.gerenciador_tarefas.dto.UserCreateDTO;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;
import com.victorbarreto.gerenciador_tarefas.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public UserModel createUser(UserCreateDTO userCreateDTO) {
        UserModel userModel = new UserModel();

        if (userRepository.existsByUsername(userCreateDTO.username())) {
            throw new RuntimeException("User already exists!!");
        }

        userModel.setUsername(userCreateDTO.username());
        String senha = securityConfig.passwordEncoder().encode(userCreateDTO.password());
        userModel.setPassword(senha);
        userModel.setRole(userCreateDTO.role());
        userModel.setEnable(true);

        return userRepository.save(userModel);
    }

}
