package com.example.api.coffee.service;

import com.example.api.coffeeDescription.service.CoffeeDescriptionService;
import com.example.dto.CoffeeDTO;
import com.example.entity.Coffee;
import com.example.exceptions.CoffeeNotFoundException;
import com.example.mapper.CoffeeMapper;
import com.example.repo.CoffeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CoffeeService {

    @Autowired
    private CoffeeRepo coffeeRepo;

    @Autowired
    private CoffeeDescriptionService coffeeDescriptionService;


    public CoffeeDTO addNewCoffee(CoffeeDTO coffeeDTO) {

        Coffee savedCoffee = coffeeRepo.save(CoffeeMapper.toEntity(coffeeDTO));

        log.info("saved coffee: {{}}", savedCoffee);

        return CoffeeMapper.toDTO(savedCoffee);
    }

    public CoffeeDTO getCoffeeById(Long coffeeId) {

        Coffee coffee = coffeeRepo.findById(coffeeId).orElseThrow(CoffeeNotFoundException::new);

        log.info("Нашлась запись о кофе: {{}}", coffee);

        return CoffeeMapper.toDTO(coffee);
    }
}
