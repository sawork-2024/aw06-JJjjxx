package com.pos.category.mapper;


import com.pos.dto.CategoryDto;
import com.pos.category.model.Category;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface CategoryMapper {
    
    Collection<CategoryDto> toCategoriesDto(Collection<Category> products);

    Collection<Category> toCategories(Collection<CategoryDto> products);

    Category toCategory(CategoryDto productDto);

    CategoryDto toCategoryDto(Category pet);

}
