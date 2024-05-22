package com.example.demo.services;
import com.example.demo.entity.ProductsEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.entity.SectionsEntity;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    IProductService iSectionService;

    public List<ProductsEntity> loadAllBooks() {
        return (List<ProductsEntity>) iSectionService.findAll();
    }

    public ProductsEntity addProduct(ProductsEntity product) {
        return iSectionService.save(product);
    }

    public void Delete(ProductsEntity sections) {
        iSectionService.delete(sections);
    }

    public ProductsEntity getById(Integer id) {
        return iSectionService.findById(id).orElse(null);
    }

    public ProductsEntity update(ProductsEntity student) {
        ProductsEntity updateResponse = iSectionService.save(student);
        return updateResponse;
    }
}