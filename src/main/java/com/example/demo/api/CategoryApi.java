package com.example.demo.api;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryApi {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategoryList(){
        return categoryRepository.findAll();
    }

    public Category getCategoryByPart(String part){
        return categoryRepository.findByPart(part);
    }
}
