package com.example.demo.services;
import com.example.demo.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService extends CrudRepository<UserEntity,Integer> {

    UserEntity findByName(String name);

    @Query("update UserEntity e set e.deleted=true where e.idUser=?1")
    @Transactional
    @Modifying
    void softDelete(Integer id);
}
