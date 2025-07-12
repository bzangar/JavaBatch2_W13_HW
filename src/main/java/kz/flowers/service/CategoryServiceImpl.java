package kz.flowers.service;

import kz.flowers.exception.CategoryNotFoundException;
import kz.flowers.model.FlowerMapper;
import kz.flowers.model.dto.CategoryDto;
import kz.flowers.model.entity.Category;
import kz.flowers.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final FlowerMapper mapper;


    @Override
    public CategoryDto getCategoryDtoById(Integer id) {
        return mapper.fromEntityToDto(getCategoryById(id));
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not exists!!"));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(mapper::fromEntityToDto).toList();
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();
        category = categoryRepository.save(category);
        return mapper.fromEntityToDto(category);
    }
}
