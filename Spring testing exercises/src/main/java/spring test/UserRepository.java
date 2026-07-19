package com.example.springtest.repository;

import com.example.springtest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  
    List<User> findByName(String name);
}
