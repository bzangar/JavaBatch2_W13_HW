package kz.flowers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.flowers.controller.CategoryController;
import kz.flowers.model.dto.CategoryDto;
import kz.flowers.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



// HARD
// HARD
// HARD

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig{
        @Bean
        public CategoryService categoryService(){
            return Mockito.mock(CategoryService.class);
        }
    }

    @Test
    public void testGetAllCategories() throws Exception{
        List<CategoryDto> categories = List.of(
                CategoryDto.builder().id(1).name("Праздничные").build(),
                CategoryDto.builder().id(2).name("Свидание").build()
        );

        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/categories/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[1].name").value("Свидание"));
    }


    // тестовые классы всегда void типа!!!
    // тестовые классы всегда void типа!!!
    // тестовые классы всегда void типа!!!

    @Test
    public void testGetCategoryById() throws Exception{
        CategoryDto categoryDto = CategoryDto.builder().id(1).name("Свидание").build();
        Mockito.when(categoryService.getCategoryDtoById(1)).thenReturn(categoryDto);

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Свидание"));
    }

    @Test
    public void testCreateCategory() throws Exception {
        CategoryDto inputDto = CategoryDto.builder().id(1).name("Свидание").build();
        CategoryDto responseDto = CategoryDto.builder().id(1).name("Праздничные").build();

        Mockito.when(categoryService.createCategory(inputDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/categories/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Праздничные"));
    }

}
