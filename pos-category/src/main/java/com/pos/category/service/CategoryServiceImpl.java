package com.pos.category.service;

import com.pos.category.model.*;
import com.pos.category.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j // Lombok annotation to autocreate an Slf4j-based LoggerFactory as log
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(@Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }




    @Override
    public List<Category> categories() {
        return categoryRepository.allCategories();
    }

}
