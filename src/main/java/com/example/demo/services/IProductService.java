package com.example.demo.services;



import com.example.demo.entity.ProductsEntity;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IProductService extends CrudRepository<ProductsEntity, Integer> {

}