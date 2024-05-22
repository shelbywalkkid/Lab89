package com.example.demo.services;

import com.example.demo.entity.SectionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ISectionService extends CrudRepository<SectionsEntity, Integer> {

}