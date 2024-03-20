package com.tynuser.service;


import com.tynentity.Category;
import com.tynuser.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found category with id: " + id));
    }

    public List<Category> listAll() {
        return (List<Category>) categoryRepository.findAll();
    }


}
