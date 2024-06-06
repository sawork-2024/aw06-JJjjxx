package com.pos.category.rest;

import com.pos.category.model.Category;

import com.pos.api.CategoriesApi;
import com.pos.dto.CategoryDto;

import com.pos.category.mapper.CategoryMapper;
import com.pos.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
public class CategoryController implements CategoriesApi {

    private final CategoryMapper categoryMapper;

    private final CategoryService productService;

    public CategoryController(CategoryService productService, CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<Category> categories = this.productService.categories();
        Collection<CategoryDto> categoriesCollection = categoryMapper.toCategoriesDto(categories);
        List<CategoryDto> categoryDtos = new ArrayList<>(categoriesCollection);
        if (categoryDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }
}
