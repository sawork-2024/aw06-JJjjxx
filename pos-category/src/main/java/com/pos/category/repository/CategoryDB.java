package com.pos.category.repository;

import com.pos.category.model.Category;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDB implements CategoryRepository{
    private List<Category> categories = null;

    CategoryDB() {
        categories = new ArrayList<>();
        categories.add(new Category("1711853606", "drink"));
    }

    @Override
    public List<Category> allCategories() {
        return categories;
    }

}
