package com.example.mockitodeps.repository;

import com.example.mockitodeps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
