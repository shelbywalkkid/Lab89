package com.example.demo.services;

import com.example.demo.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDAO extends UserDetailsService {
    UserEntity findByUserName(String name);

    UserEntity addUser(UserEntity user);

    List<UserEntity> findAllUsers();

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    void safeDelete(UserEntity myUser);
}
