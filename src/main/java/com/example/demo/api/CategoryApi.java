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

    public Category getCategoryByPart(String part){
        return categoryRepository.findByPart(part);
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getCategoryList(){
        return categoryRepository.findAll();
    }

    public void remove(Category category){
        categoryRepository.delete(category);
    }
}
