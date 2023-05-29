package com.expatrio.user.domain.repository;

import com.expatrio.user.domain.Role;
import com.expatrio.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>  findByUsername(String username);

    List<User> findAllByRole(Role role);

}