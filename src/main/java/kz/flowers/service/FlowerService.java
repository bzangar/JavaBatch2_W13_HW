package kz.flowers.service;

import kz.flowers.model.dto.FlowerDto;

import java.util.List;

public interface FlowerService {

    List<FlowerDto> getAllFlowers();

    FlowerDto getFlowerById(Integer id);

    FlowerDto createFlower(FlowerDto flowerDto);

    boolean deleteFlowerById(Integer id);
}
