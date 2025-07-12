package kz.flowers.service;

import kz.flowers.model.dto.CategoryDto;
import kz.flowers.model.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategoryDtoById(Integer id);

    Category getCategoryById(Integer id);

    List<CategoryDto> getAllCategories();

    CategoryDto createCategory(CategoryDto categoryDto);
}
