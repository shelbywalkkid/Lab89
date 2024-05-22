package com.example.demo.services;

import com.example.demo.controller.MyUserDetails;
import com.example.demo.entity.ProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserEntity;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDAO {
    @Autowired
    IUserService entityRepository;

    @Override
    public UserEntity findByUserName(String name) {
        return entityRepository.findByName(name);
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        return entityRepository.save(user);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return (List<UserEntity>) entityRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = Optional.ofNullable(entityRepository.findByName(username));
        return user.map(MyUserDetails::new).orElse(null);
    }

    public UserEntity getById(Integer id) {
        return entityRepository.findById(id).orElse(null);
    }

    public void Delete(UserEntity sections) {
        entityRepository.delete(sections);
    }

    @Override
    public void safeDelete(UserEntity myUser) {
        entityRepository.softDelete(myUser.getIdUser());
        entityRepository.save(myUser);
    }
}