package com.example.autoAlert360Pro.services;

import com.example.autoAlert360Pro.entities.User;
import com.example.autoAlert360Pro.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null); // Devuelve null si el usuario no existe
    }
}