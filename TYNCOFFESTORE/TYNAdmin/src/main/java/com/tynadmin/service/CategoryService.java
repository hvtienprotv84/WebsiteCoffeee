package com.tynadmin.service;

import com.tynadmin.exception.NotFoundException;
import com.tynadmin.repository.CategoryRepository;
import com.tynadmin.util.FileUploadUtils;
import com.tynadmin.util.RootPathImageUtils;
import com.tynentity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    public Category get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category with id: " + id));
    }

    public List<Category> listAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    public void delete(Integer id) {
        Category category = get(id);
        FileUploadUtils.cleanDir(RootPathImageUtils.CATEGORY + "/" + id + "/");
        FileUploadUtils.delete(RootPathImageUtils.CATEGORY + "/" + id + "/");
        categoryRepository.delete(category);
    }

}
