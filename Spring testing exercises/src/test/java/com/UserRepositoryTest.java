package com.example.springtest.repository;

import com.example.springtest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 7: Test Custom Repository Query.
 * @DataJpaTest spins up an in-memory H2 database and only the JPA layer.
 */
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByName() {
        userRepository.save(new User("Alice"));
        userRepository.save(new User("Bob"));
        userRepository.save(new User("Alice"));

        List<User> results = userRepository.findByName("Alice");

        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(u -> u.getName().equals("Alice")));
    }

    @Test
    void testFindByName_noMatch() {
        userRepository.save(new User("Charlie"));

        List<User> results = userRepository.findByName("Nonexistent");

        assertTrue(results.isEmpty());
    }
}
