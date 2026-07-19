package com.example.springtest.service;

import com.example.springtest.model.User;
import com.example.springtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * NOTE: the original exercise used `orElse(null)`. It's changed here to
     * `orElseThrow(...)` so Exercise 6 (service exception handling) and
     * Exercise 8 (@ControllerAdvice exception handling) have something
     * meaningful to test.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
