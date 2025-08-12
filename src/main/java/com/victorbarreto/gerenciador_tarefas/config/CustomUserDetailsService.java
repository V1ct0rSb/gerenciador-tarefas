package com.victorbarreto.gerenciador_tarefas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.victorbarreto.gerenciador_tarefas.entity.UserModel;
import com.victorbarreto.gerenciador_tarefas.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username);

        if (userModel == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!!");
        }

        return User.builder()
            .username(userModel.getUsername())
            .password(userModel.getPassword())
            .roles(userModel.getRole().name())
            .build();
    }
}
