package com.example.springtest.controller;

import com.example.springtest.model.User;
import com.example.springtest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercise 3: Testing a REST Controller with MockMvc (GET).
 * Exercise 5: Test Controller POST Endpoint.
 * Exercise 8: Test Controller Exception Handling (@ControllerAdvice).
 *
 * @WebMvcTest loads only the web layer (controller + advice), with the
 * service layer mocked out via @MockBean.
 */
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser_returnsUser() throws Exception {
        User user = new User(1L, "John Doe");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testCreateUser_returnsCreatedUser() throws Exception {
        User requestUser = new User("Jane Doe");
        User savedUser = new User(1L, "Jane Doe");
        when(userService.saveUser(org.mockito.ArgumentMatchers.any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    void testGetUser_notFound_returns404() throws Exception {
        when(userService.getUserById(99L))
                .thenThrow(new NoSuchElementException("User not found with id 99"));

        mockMvc.perform(get("/users/{id}", 99L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
