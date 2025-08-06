package org.example.hero.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final org.example.hero.repository.UserRepository userRepository;

    public SecurityConfig(org.example.hero.repository.UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // UserDetailsService — загружает пользователя по username из базы и возвращает объект UserDetails
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            org.example.hero.model.User appUser = userRepository.findByUsername(username);
            if (appUser == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return User.builder()
                    .username(appUser.getUsername())
                    .password(appUser.getPassword()) // пароль уже должен быть зашифрован
                    .authorities(List.of(() -> "ROLE_USER"))  // роли пользователя
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/database", "/login", "/css/**").permitAll() // страницы для всех
                        .anyRequest().authenticated() // все остальные требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login")  // твоя страница логина
                        .defaultSuccessUrl("/edit", true) // куда редирект после успешного входа
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}
