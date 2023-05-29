package com.expatrio.user.controller;

import com.expatrio.user.config.JwtTokenProvider;
import com.expatrio.user.domain.Role;
import com.expatrio.user.domain.User;
import com.expatrio.user.service.UserService;
import com.expatrio.user.input.UserInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class UserControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    private MockMvc mockMvc;

    @Test
    void createUser() {
    }

    @Test
    public void testCreateUser_whenOk() throws Exception {
        UserInput userInput = createUserInput();
        given(userService.createUser(any(UserInput.class))).willReturn(new User());

        this.mockMvc.perform(MockMvcRequestBuilders.
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userInput))
                        .with(user("user").roles("ADMIN")))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteUser() throws Exception {
        Long userId = 1L;
        this.mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNoContent());
        verify(userService).deleteUserById(userId);
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUsers() {
    }

    private UserInput createUserInput() {
        return UserInput.builder()
                .username("username")
                .password("password")
                .role(Role.CUSTOMER.name())
                .firstname("ali")
                .lastname("hlayel")
                .build();
    }

    private UserInput updateUserInput() {
        return UserInput.builder()
                .password("new Pass")
                .build();
    }

    private String asJsonString(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}