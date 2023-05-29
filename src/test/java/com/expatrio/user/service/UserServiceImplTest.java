package com.expatrio.user.service;

import com.expatrio.user.domain.Role;
import com.expatrio.user.domain.User;
import com.expatrio.user.domain.repository.UserRepository;
import com.expatrio.user.exception.UserException;
import com.expatrio.user.input.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void deleteUserById() {
        User user = createUser(1L, "ADMIN");
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        userService.deleteUserById(1L);
        verify(userRepository, Mockito.times(1)).delete(user);
    }

    @Test
    void deleteUserById_throw_not_found() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserException.class, () -> userService.deleteUserById(1L));
    }

    @Test
    void createUser() {

        User user = createUser(1L, "ADMIN");
        UserInput userInput = createUserInput(1L, "ADMIN");
        Mockito.when(passwordEncoder.encode(userInput.getPassword())).thenReturn(userInput.getPassword());
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(userInput);

        assertEquals(userInput.getUsername(), createdUser.getUsername());
        assertEquals(userInput.getPassword(), createdUser.getPassword());
        assertEquals(Role.ADMIN, createdUser.getRole());
        verify(userRepository, Mockito.times(1)).save(any(User.class));

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals(userInput.getUsername(), savedUser.getUsername());
        assertEquals(userInput.getPassword(), savedUser.getPassword());
        assertEquals(Role.ADMIN, savedUser.getRole());
    }

    @Test
    void updateUser_when_Ok() {
        Long userId = 1L;
        UserInput userInput = createUserInput(2L, "ADMIN");
        User existingUser = createUser(1L, "CUSTOMER") ;

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder);

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(existingUser));
        when(passwordEncoder.encode(userInput.getPassword())).thenReturn(existingUser.getPassword());
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(userId, userInput);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(existingUser.getUsername(), result.getUsername());
        assertEquals(userInput.getPassword(), result.getPassword());
        assertEquals(existingUser.getRole(), result.getRole());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals(userInput.getPassword(), capturedUser.getPassword());
    }

    @Test
    public void updateUser_when_user_not_found() {
        Long userId = 1L;
        UserInput userInput = createUserInput(3L, "ADMIN");

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder);
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());
        assertThrows(UserException.class, () -> userService.updateUser(userId, userInput));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getAllUsers() {
        User user1 = createUser(1L, "CUSTOMER");
        User user2 = createUser(1L, "CUSTOMER");
        List<User> users = Arrays.asList(user1, user2);

        Mockito.when(userRepository.findAllByRole(Role.CUSTOMER)).thenReturn(users);
        List<User> result = userService.getAllUsers();
        assertEquals(users.size(), result.size());
        assertEquals(users.get(0), result.get(0));
        assertEquals(users.get(1), result.get(1));


    }

    private UserInput createUserInput(Long regex, String role) {
        return UserInput.builder()
                .firstname("firstname" + regex)
                .password(passwordEncoder.encode("user" + regex))
                .username("username" + regex)
                .role(role).build();
    }


    private User createUser(Long regex, String role) {
        return User.builder()
                .id(regex)
                .firstname("firstname" + regex)
                .password(passwordEncoder.encode("user" + regex))
                .username("username" + regex)
                .role(Role.valueOf(role)).build();
    }
}