package com.victorbarreto.gerenciador_tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.victorbarreto.gerenciador_tarefas.dto.UserCreateDTO;
import com.victorbarreto.gerenciador_tarefas.entity.UserModel;
import com.victorbarreto.gerenciador_tarefas.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserModel userModel = userService.createUser(userCreateDTO);
        return ResponseEntity.status(201).body(userModel);
    }

    //Somente ADM
    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> displayUsers() {
        List<UserModel> userModel = userService.displayUsers();
        return ResponseEntity.ok().body(userModel);
    }
}
