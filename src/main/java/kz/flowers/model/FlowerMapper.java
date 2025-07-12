package kz.flowers.model;

import kz.flowers.model.dto.CategoryDto;
import kz.flowers.model.dto.FlowerDto;
import kz.flowers.model.entity.Category;
import kz.flowers.model.entity.Flower;
import org.springframework.stereotype.Component;

@Component
public class FlowerMapper { // этот класс преобразует Flower в FlowerDto. То есть entity в DTO
    public FlowerDto fromEntityToDto(Flower flower){
        return FlowerDto.builder()
                .id(flower.getId())
                .name(flower.getName())
                .price(flower.getPrice())
                .category(fromEntityToDto(flower.getCategory()))
                .build();
    }

    public CategoryDto fromEntityToDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
