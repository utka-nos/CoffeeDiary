package com.example.api.coffeeDescription.service;

import com.example.dto.CoffeeDescriptionDTO;
import com.example.entity.CoffeeDescription;
import com.example.exceptions.CoffeeDescriptionNotFoundException;
import com.example.mapper.CoffeeDescriptionMapper;
import com.example.repo.CoffeeDescriptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class CoffeeDescriptionService {

    @Autowired
    private CoffeeDescriptionRepo coffeeDescriptionRepo;

    /** Добавление нового coffeeDescription
     *
     * @param coffeeDescriptionDTO - новый coffeeDescriptionDTO переданный от пользователя
     * @return - сохраненная версия coffeeDescription (с присвоенным id и прочими полями),
     * конвертированная в DTO
     */
    public CoffeeDescriptionDTO saveCoffeeDescription(CoffeeDescriptionDTO coffeeDescriptionDTO) {
        return saveCoffeeDescription(CoffeeDescriptionMapper.toEntity(coffeeDescriptionDTO));
    }

    /** Добавление нового coffeeDescription
     *
     * @param coffeeDescription - новый coffeeDescription переданный от пользователя
     * @return - сохраненная версия coffeeDescription (с присвоенным id и прочими полями)
     */
    public CoffeeDescriptionDTO saveCoffeeDescription(CoffeeDescription coffeeDescription) {
        CoffeeDescription savedCoffeeDescription = coffeeDescriptionRepo.save(coffeeDescription);

        log.info("attempt to save new coffeeDescription: {{}}", savedCoffeeDescription);

        return CoffeeDescriptionMapper.toDTO(savedCoffeeDescription);
    }

    /** Получение CoffeeDescription по его id
     *
     * @param descriptionId - id от coffeeDescription
     * @return - Получаем нужный coffeeDescription
     * @throws CoffeeDescriptionNotFoundException - показывает, что coffeeDescription с таким id не найден
     */
    public CoffeeDescriptionDTO getDescriptionById(Long descriptionId) throws CoffeeDescriptionNotFoundException{

        CoffeeDescription coffeeDescription =
                coffeeDescriptionRepo.findById(descriptionId).orElseThrow(CoffeeDescriptionNotFoundException::new);

        log.info("Attempt to get coffeeDescription from CoffeeDescriptionService: {{}}", coffeeDescription);

        return CoffeeDescriptionMapper.toDTO(coffeeDescription);
    }

    /** Получение списка всех CoffeeDescription
     *
     * @return - список всех coffeeDescription
     */
    public List<CoffeeDescriptionDTO> getAllCoffeeDescriptions() {
        List<CoffeeDescription> allCoffeeDescriptions = coffeeDescriptionRepo.findAll();
        return coffeeDescriptionRepo.findAll()
                .stream()
                .map(CoffeeDescriptionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
