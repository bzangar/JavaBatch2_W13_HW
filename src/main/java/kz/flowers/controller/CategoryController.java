package kz.flowers.controller;

import kz.flowers.model.dto.CategoryDto;
import kz.flowers.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(
            @PathVariable Integer id
    ){
        return categoryService.getCategoryDtoById(id);
    }

    @PostMapping("/add")
    public CategoryDto createCategory(
            @RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }
}
