package com.expatrio.user.service;

import com.expatrio.user.domain.User;
import com.expatrio.user.domain.repository.UserRepository;
import com.expatrio.user.exception.UserError;
import com.expatrio.user.exception.UserException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user1 = userRepository.findByUsername(username).orElse(null);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));

        UserDetails details = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())))
                .build();
        return details;
    }
}
