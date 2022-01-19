package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    List<User> findAll();
    Optional<User> findByUsername(String username);
    User findOne(int id);
    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
}
