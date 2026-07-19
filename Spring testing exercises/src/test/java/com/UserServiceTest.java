package com.example.springtest.service;

import com.example.springtest.model.User;
import com.example.springtest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Exercise 2: Mocking a Repository in a Service Test.
 * Exercise 6: Test Service Exception Handling.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById_found() {
        User mockUser = new User(1L, "John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserById_notFound_throwsException() {
        // Exercise 6: service should surface a clear exception for a missing user
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> userService.getUserById(99L)
        );

        assertTrue(exception.getMessage().contains("99"));
    }

    @Test
    void testSaveUser() {
        User newUser = new User("Jane Doe");
        User savedUser = new User(1L, "Jane Doe");
        when(userRepository.save(newUser)).thenReturn(savedUser);

        User result = userService.saveUser(newUser);

        assertEquals(1L, result.getId());
        assertEquals("Jane Doe", result.getName());
    }
}
