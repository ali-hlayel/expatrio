package com.expatrio.user.controller;

import com.expatrio.user.domain.User;
import com.expatrio.user.dto.UserDto;
import com.expatrio.user.dto.UserToUserDto;
import com.expatrio.user.input.UserInput;
import com.expatrio.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Api(tags = "User Service Controller")
@RequestMapping(name = "/users", produces = MediaType.APPLICATION_JSON_VALUE)

public class UserController {

    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Create new User")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserInput user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserToUserDto.convertUserToUserDto(createdUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("delete User")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Update User")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserInput user) {
        User updatedUser = userService.updateUser(id, user);

        return ResponseEntity.ok(UserToUserDto.convertUserToUserDto(updatedUser));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> getUsers() {
        return userService.getAllUsers().stream().map(UserToUserDto::convertUserToUserDto)
                .collect(Collectors.toList());
    }
}
