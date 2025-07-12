package kz.flowers.service;

import kz.flowers.exception.FlowerNotFoundException;
import kz.flowers.model.FlowerMapper;
import kz.flowers.model.dto.FlowerDto;
import kz.flowers.model.entity.Flower;
import kz.flowers.repository.FlowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService{

    private final FlowerRepository flowerRepository;
    private final FlowerMapper mapper;
    private final CategoryService categoryService;


    @Override
    public List<FlowerDto> getAllFlowers() {
        List<Flower> flowers = flowerRepository.findAll();
        return flowers.stream().
                map(mapper::fromEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public FlowerDto getFlowerById(Integer id) {
        Flower flower = flowerRepository.findById(id)
                .orElseThrow(() -> new FlowerNotFoundException
                        ("Flower is not exists!!!"));// выводим исключение когда Optional равен null
        // если делам orElseThrow() то нам передает не Optional, а Flower

        return mapper.fromEntityToDto(flower);
    }

    @Override
    public FlowerDto createFlower(FlowerDto flowerDto) {
        Flower flower = Flower.builder()
                .name(flowerDto.getName())
                .size(flowerDto.getSize())
                .price(flowerDto.getPrice())
                .category(categoryService.getCategoryById(flowerDto.getCategory().getId()))
                .build();
        flower = flowerRepository.save(flower); // сохранили flower в репозитории
        return mapper.fromEntityToDto(flower);
    }


    @Override
    public boolean deleteFlowerById(Integer id) {
        if(!flowerRepository.existsById(id)){
            throw new FlowerNotFoundException("Flower does not exists!!!");
        }
        if(id == null){
            throw new FlowerNotFoundException("Id ust not be null!!!");
        }

        flowerRepository.deleteById(id);
        return true;
    }
}
