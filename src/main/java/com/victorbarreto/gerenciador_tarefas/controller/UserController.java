package com.victorbarreto.gerenciador_tarefas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.victorbarreto.gerenciador_tarefas.dto.UserCreateDTO;
import com.victorbarreto.gerenciador_tarefas.dto.UserResponseDTO;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> displayUsers() {
        List<UserModel> userModel = userService.displayUsers();
        return ResponseEntity.ok().body(userModel);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/buscar")
    public ResponseEntity<List<UserResponseDTO>> searchByParameters(@RequestParam(required = false) Long id,
                                                                    @RequestParam(required = false) String username) {
        List<UserModel> resultado = userService.searchByExample(username, id);
        List<UserResponseDTO> list = resultado.stream()
            .map(user -> new UserResponseDTO(user.getId(), user.getUsername(), user.getRole()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{username}")
    public ResponseEntity<UserModel> modifyUser(@PathVariable String username,
                                                      @RequestBody UserCreateDTO userCreateDTO) {
        UserModel userModel = userService.modifyUser(username, userCreateDTO);
        return ResponseEntity.ok().body(userModel);
    }
}
