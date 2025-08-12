package com.victorbarreto.gerenciador_tarefas.config;

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
        UserModel userModel = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found!"));

//        if (userModel == null) {
//            throw new UsernameNotFoundException("User not found!");
//        }

        return User.builder()
            .username(userModel.getUsername())
            .password(userModel.getPassword())
            .roles(userModel.getRole().name())
            .build();
    }
}
