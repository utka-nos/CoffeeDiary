package com.example.api.coffeeDescription.service;

import com.example.dto.CoffeeDescriptionDTO;
import com.example.entity.CoffeeDescription;
import com.example.exceptions.CoffeeDescriptionNotFoundException;
import com.example.mapper.CoffeeDescriptionMapper;
import com.example.repo.CoffeeDescriptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CoffeeDescriptionService {

    @Autowired
    private CoffeeDescriptionRepo coffeeDescriptionRepo;

    public CoffeeDescriptionDTO saveCoffeeDescription(CoffeeDescriptionDTO coffeeDescriptionDTO) {
        return saveCoffeeDescription(CoffeeDescriptionMapper.toEntity(coffeeDescriptionDTO));
    }

    public CoffeeDescriptionDTO saveCoffeeDescription(CoffeeDescription coffeeDescription) {
        CoffeeDescription savedCoffeeDescription = coffeeDescriptionRepo.save(coffeeDescription);

        log.info("attempt to save new coffeeDescription: {{}}", savedCoffeeDescription);

        return CoffeeDescriptionMapper.toDTO(savedCoffeeDescription);
    }

    public CoffeeDescriptionDTO getDescriptionById(Long descriptionId) {

        CoffeeDescription coffeeDescription =
                coffeeDescriptionRepo.findById(descriptionId).orElseThrow(CoffeeDescriptionNotFoundException::new);

        log.info("Attempt to get coffeeDescription from CoffeeDescriptionService: {{}}", coffeeDescription);

        return CoffeeDescriptionMapper.toDTO(coffeeDescription);
    }
}
