package com.example.autoAlert360Pro.repository;

import com.example.autoAlert360Pro.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<User,Long> {
   List<User> findByEmail(String email);


}
