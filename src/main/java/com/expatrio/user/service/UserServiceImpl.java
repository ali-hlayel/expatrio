package com.expatrio.user.service;

import com.expatrio.user.domain.Role;
import com.expatrio.user.domain.User;
import com.expatrio.user.domain.repository.UserRepository;
import com.expatrio.user.exception.UserError;
import com.expatrio.user.exception.UserException;
import com.expatrio.user.input.UserInput;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void deleteUserById(Long userId) {


        userRepository.deleteById(userId);
    }

    public User createUser(UserInput createUserInput) {
        String password = passwordEncoder.encode(createUserInput.getPassword());
        createUserInput.setPassword(password);
        User user = new User();
        BeanUtils.copyProperties(createUserInput, user);
        user.setRole(Role.valueOf(createUserInput.getRole()));
        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserInput userInput) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
        String password = passwordEncoder.encode(userInput.getPassword());
        existingUser.setPassword(password);
        return userRepository.save(existingUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByRole(Role.CUSTOMER);
    }
}
