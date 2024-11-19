package com.example.autoAlert360Pro.repository;

import com.example.autoAlert360Pro.entities.Role;
import com.example.autoAlert360Pro.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
