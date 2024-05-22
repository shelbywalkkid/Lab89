package com.example.demo.services;

import com.example.demo.entity.SectionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionsService {


    @Autowired
    ISectionService iSectionService;

    public List<SectionsEntity> loadAllSections() {
        return (List<SectionsEntity>) iSectionService.findAll();
    }

    public SectionsEntity addProduct(SectionsEntity product) {return iSectionService.save(product);}

    public void Delete(SectionsEntity sections) {
        iSectionService.delete(sections);
    }

    public SectionsEntity getById(Integer id) {
        return iSectionService.findById(id).orElse(null);
    }
    public SectionsEntity update(SectionsEntity student) {
        SectionsEntity updateResponse = iSectionService.save(student);
        return updateResponse;
    }

}