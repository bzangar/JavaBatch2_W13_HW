package kz.flowers;


import kz.flowers.exception.CategoryNotFoundException;
import kz.flowers.model.FlowerMapper;
import kz.flowers.model.dto.CategoryDto;
import kz.flowers.model.entity.Category;
import kz.flowers.repository.CategoryRepository;
import kz.flowers.service.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


// EASY & MEDIUM

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private FlowerMapper mapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void testGetCategoryById_isEmpty(){
        Integer id = 1;

        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(id));

        // HARD
        // HARD
        // HARD
        verify(categoryRepository, atLeast(1)).findById(id);
    }

    @Test
    public void testGetAllCategories(){
        List<Category> categories = List.of(
                Category.builder().id(1).name("Праздник").build(),
                Category.builder().id(2).name("Свидание").build()
        );
        List<CategoryDto> categoryDtosList = List.of(
                CategoryDto.builder().id(1).name("Праздник").build(),
                CategoryDto.builder().id(2).name("Свидание").build()
        );

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Mockito.when(mapper.fromEntityToDto(categories.get(0))).thenReturn(categoryDtosList.get(0));
        Mockito.when(mapper.fromEntityToDto(categories.get(1))).thenReturn(categoryDtosList.get(1));
        //
        //
        List<CategoryDto> result = categoryService.getAllCategories();
        //
        //
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Свидание", result.get(1).getName());


        // HARD
        // HARD
        // HARD
        verify(mapper, times(2)).fromEntityToDto(any(Category.class));
    }

    @Test
    public void testCreateCategory(){
        CategoryDto inputCategoryDto = CategoryDto.builder().id(1).name("Праздник").build();

        Category category = Category.builder().id(1).name("Праздник").build();

        CategoryDto categoryDto = CategoryDto.builder().id(1).name("Праздник").build();

        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Mockito.when(mapper.fromEntityToDto(category)).thenReturn(categoryDto);
        //
        //
        CategoryDto result = categoryService.createCategory(inputCategoryDto);
        //
        //
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("Праздник", result.getName());


        // HARD
        // HARD
        // HARD
        verify(mapper, atLeastOnce()).fromEntityToDto(any(Category.class));
    }

    @Test
    public void testGetCategoryDtoById_whenEntityNotFound(){
        Category category = null;
        Mockito.when(categoryRepository.findById(any())).thenReturn(null);

        Assertions.assertThrows(NullPointerException.class, () -> categoryService.getCategoryById(any()));

        // HARD
        // HARD
        // HARD
        verify(categoryRepository).findById(any());
    }
}
