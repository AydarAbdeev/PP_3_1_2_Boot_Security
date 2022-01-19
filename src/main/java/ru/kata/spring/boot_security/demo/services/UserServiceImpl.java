package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = findByUsername( username );

        if (user.isEmpty()) {
            throw new UsernameNotFoundException( "User not found" );
        }
        return user.get();
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername( username );
    }


    @Override
    public User findOne(int id) {
        return userRepository.findById( id ).orElse( null );
    }

    @Transactional
    @Override
    public void save(User user) {
        user.setPassword( new BCryptPasswordEncoder().encode( user.getPassword() ) );
        user.setRoles( user.getRoles() );
        userRepository.save( user );
    }

    @Transactional
    @Override
    public void update(int id, User updatedUser) {
        updatedUser.setId( id );
        updatedUser.setPassword( new BCryptPasswordEncoder().encode( updatedUser.getPassword() ) );
        updatedUser.setRoles( updatedUser.getRoles() );
        userRepository.save( updatedUser );
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.deleteById( id );
    }
}
