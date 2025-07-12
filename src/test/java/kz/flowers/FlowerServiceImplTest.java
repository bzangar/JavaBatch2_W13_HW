package kz.flowers;

import kz.flowers.exception.FlowerNotFoundException;
import kz.flowers.model.FlowerMapper;
import kz.flowers.model.dto.CategoryDto;
import kz.flowers.model.dto.FlowerDto;
import kz.flowers.model.entity.Category;
import kz.flowers.model.entity.Flower;
import kz.flowers.repository.CategoryRepository;
import kz.flowers.repository.FlowerRepository;
import kz.flowers.service.CategoryService;
import kz.flowers.service.FlowerService;
import kz.flowers.service.FlowerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension; // подключается JUnit

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class) // мы говорим что хотим использовать Mockito
public class FlowerServiceImplTest {

    @Mock // для создание пустого объекта. Он не подключается в базе данных
    private FlowerRepository flowerRepository; // не пишем final
    @Mock
    private FlowerMapper mapper;
    @Mock
    private CategoryService categoryService;


    // создаем объект FlowerServiceImplTest которого мы тестируем
    // и устанавливаем зависимости с помощью @InjectMocks
    @InjectMocks
    private FlowerServiceImpl flowerService;


    // тестируем метод FlowerServiceImpl
    @Test
    public void testGetAllFlowers(){
        List<Flower> flowers = List.of(
                Flower.builder().id(1).name("Роза").build(),
                Flower.builder().id(2).name("Пионы").build()
        ); // создаем фейковые объекты Flower для теста. Они имитируют как будто они пришли из базы данных

        List<FlowerDto> flowerDtoList = List.of(
                FlowerDto.builder().id(1).name("Роза").build(),
                FlowerDto.builder().id(2).name("Пионы").build()
        );

        Mockito.when(flowerRepository.findAll()).thenReturn(flowers); // имитируем поведение flowerRepository.findAll()
        Mockito.when(mapper.fromEntityToDto(flowers.get(0))).thenReturn(flowerDtoList.get(0));
        Mockito.when(mapper.fromEntityToDto(flowers.get(1))).thenReturn(flowerDtoList.get(1));
        //
        //
        List<FlowerDto> result = flowerService.getAllFlowers(); // метод который  тестируется -> flowerService.getAllFlowers()
        //
        //
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Роза", result.get(0).getName()); // на этом тест метода закончен
    }

    @Test
    public void testGetFlowerById_whenNotExists(){ // тестируем случай когда у нас нет цветка
        Mockito.when(flowerRepository.findById(any())).thenReturn(Optional.empty()); // иммитирует поведение flowerRepository.findById
        // any() возвращает любое значение
        Assertions.assertThrows(FlowerNotFoundException.class, () -> flowerService.getFlowerById(any())); // проверяем на исключение
    }

    @Test
    public void testCreateFlower(){
        Category category = Category.builder().id(1).name("8 mart").build();

        FlowerDto inputFlowerDto = FlowerDto.builder()
                .name("Роза")
                .size("M")
                .price(BigDecimal.valueOf(100))
                .category(CategoryDto.builder().id(1).build())
                .build();

        Flower savedFlower = Flower.builder()
                .id(1)
                .name("Роза")
                .size("M")
                .price(BigDecimal.valueOf(100))
                .category(category)
                .build();

        FlowerDto flowerDto = FlowerDto.builder()
                .id(1)
                .name("Роза")
                .size("M")
                .price(BigDecimal.valueOf(100))
                .category(CategoryDto.builder().id(1).build())
                .build();

        // имитируем все методы в методе сreateFlower
        Mockito.when(categoryService.getCategoryById(1)).thenReturn(category);
        Mockito.when(flowerRepository.save(any(Flower.class))).thenReturn(savedFlower);
        Mockito.when(mapper.fromEntityToDto(savedFlower)).thenReturn(flowerDto);
        //
        //
        FlowerDto result = flowerService.createFlower(inputFlowerDto);
        //
        //
        Assertions.assertEquals("Роза", result.getName());
        Assertions.assertEquals(1, result.getCategory().getId());
    }

}
