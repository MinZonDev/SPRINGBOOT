package com.example.WEBBANMOHINH.services;

import com.example.WEBBANMOHINH.entity.Category;
import com.example.WEBBANMOHINH.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> GetAll() {
        return categoryRepository.findAll();
    }
}
