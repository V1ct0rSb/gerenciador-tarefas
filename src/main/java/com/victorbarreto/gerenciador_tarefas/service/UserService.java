package com.victorbarreto.gerenciador_tarefas.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import com.victorbarreto.gerenciador_tarefas.config.AuthorizationServerConfiguration;
import com.victorbarreto.gerenciador_tarefas.config.SecurityConfig;
import com.victorbarreto.gerenciador_tarefas.dto.UserCreateDTO;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;
import com.victorbarreto.gerenciador_tarefas.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorizationServerConfiguration authorizationServerConfiguration;

    public UserModel createUser(UserCreateDTO userCreateDTO) {
        UserModel userModel = new UserModel();

        if (userRepository.existsByUsername(userCreateDTO.username())) {
            throw new RuntimeException("User already exists!!");
        }

        userModel.setUsername(userCreateDTO.username());
        String senha = authorizationServerConfiguration.passwordEncoder().encode(userCreateDTO.password());
        userModel.setPassword(senha);
        userModel.setRole(userCreateDTO.role());
        userModel.setEnable(true);

        return userRepository.save(userModel);
    }

    public List<UserModel> displayUsers() {
        return userRepository.findAll();
    }

    public UserModel searchById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found!"));
    }

    public UserModel searchByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public List<UserModel> searchByExample(String username, Long id) {
        UserModel userModel = new UserModel();

        userModel.setUsername(username);
        userModel.setId(id);

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<UserModel> userModelExample = Example.of(userModel, matcher);

        return userRepository.findAll(userModelExample);
    }

    public UserModel modifyUser(String username, UserCreateDTO userCreateDTO) {
        UserModel userModel = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found!"));

        userModel.setUsername(userCreateDTO.username());
        String senha = authorizationServerConfiguration.passwordEncoder().encode(userCreateDTO.password());
        userModel.setPassword(senha);
        userModel.setRole(userCreateDTO.role());

        return userRepository.save(userModel);
    }
}
