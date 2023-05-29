package com.expatrio.user.service;

import com.expatrio.user.domain.User;
import com.expatrio.user.input.UserInput;

import java.util.List;

public interface UserService {

    void deleteUserById(Long userId);

    User createUser(UserInput createUser);

    User updateUser(Long userId, UserInput updatedUser);

    List<User> getAllUsers();
}
