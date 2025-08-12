package com.victorbarreto.gerenciador_tarefas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.victorbarreto.gerenciador_tarefas.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .formLogin(form -> form
                .defaultSuccessUrl("/h2-console", true)
            )
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/login", "/h2-console/**").permitAll();
                authorize.requestMatchers(HttpMethod.POST, "/api/users").permitAll();
                authorize.anyRequest().authenticated();
            }).build();
    }
}
