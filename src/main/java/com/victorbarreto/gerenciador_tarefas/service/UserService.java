package com.victorbarreto.gerenciador_tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.victorbarreto.gerenciador_tarefas.config.SecurityConfig;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;
import com.victorbarreto.gerenciador_tarefas.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public UserModel createUser(UserModel user) {
        UserModel userModel = new UserModel();

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User already exists!!");
        }

        userModel.setUsername(user.getUsername());
        String senha = securityConfig.passwordEncoder().encode(user.getPassword());
        userModel.setPassword(senha);
        userModel.setRole(user.getRole());
        userModel.setEnable(true);

        return userRepository.save(userModel);
    }

}
