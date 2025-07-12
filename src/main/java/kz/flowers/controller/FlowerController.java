package kz.flowers.controller;

import kz.flowers.model.dto.FlowerDto;
import kz.flowers.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flowers")
public class FlowerController {

    private final FlowerService flowerService;

    @GetMapping("/all")
    public List<FlowerDto> getAllFlowers(){
        return flowerService.getAllFlowers();
    }

    @GetMapping("/{id}")
    public FlowerDto getFlowerById(
            @PathVariable Integer id
    ){
        return flowerService.getFlowerById(id);
    }


    //на самом деле в постмаппинге должно быть проверка: админ ли пользователь.
    // Чтобы каждый не могу добавлять букет
    @PostMapping()
    public FlowerDto createFlower(
            @RequestBody FlowerDto flowerDto
    ){
        return flowerService.createFlower(flowerDto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteFlowerById(
            @PathVariable Integer id
    ){
        return flowerService.deleteFlowerById(id);
    }

    //CRUD
    // C - create
    // R - read
    // U - update
    // D - delete
}
