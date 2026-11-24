package com.example.EmployeeMangaementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmployeeMangaementSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
    
//    Optional<org.apache.catalina.User> findByusername(String username);

	Optional<User> findByUsername(String username);

}

