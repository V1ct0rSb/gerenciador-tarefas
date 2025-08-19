package com.victorbarreto.gerenciador_tarefas.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    @Operation(summary = "createUser", description = "create user")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "409", description = "User already registered")
        }
    )
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserModel userModel = userService.createUser(userCreateDTO);
        return ResponseEntity.status(201).body(userModel);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    @Operation(summary = "displayUsers", description = "List registered users")
    @ApiResponse(responseCode = "200", description = "User list displayed successfully")
    public ResponseEntity<List<UserResponseDTO>> displayUsers() {
        List<UserResponseDTO> userModel = userService.displayUsers();
        return ResponseEntity.ok().body(userModel);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/buscar")
    @Operation(summary = "searchByParameters", description = "Search User by ID or Name")
    @ApiResponse(responseCode = "200", description = "User list displayed successfully")
    public ResponseEntity<List<UserResponseDTO>> searchByParameters(@RequestParam(required = false) UUID id,
                                                                    @RequestParam(required = false) String username) {
        List<UserModel> resultado = userService.searchByExample(username, id);
        List<UserResponseDTO> list = resultado.stream()
            .map(user -> new UserResponseDTO(user.getId(), user.getUsername(), user.getRole()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{username}")
    @Operation(summary = "modifyUser", description = "Change user information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User modified successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserModel> modifyUser(@PathVariable String username,
                                                @RequestBody UserCreateDTO userCreateDTO) {
        UserModel userModel = userService.modifyUser(username, userCreateDTO);
        return ResponseEntity.ok().body(userModel);
    }
}
