package com.pos.category.repository;
import com.pos.category.model.Category;

import java.util.List;


public interface CategoryRepository {

    public List<Category> allCategories();
}
