package com.example.mapper;

import com.example.dto.CoffeeDTO;
import com.example.dto.CoffeeDescriptionDTO;
import com.example.entity.Coffee;
import com.example.entity.CoffeeDescription;

import java.util.List;
import java.util.stream.Collectors;

public class CoffeeMapper{

    public static Coffee toEntity(CoffeeDTO coffeeDTO) {
        Coffee coffee = new Coffee();

        coffee.setId(coffeeDTO.getId());
        coffee.setName(coffeeDTO.getName());

        List<CoffeeDescription> coffeeDescriptions =
                coffeeDTO.getCoffeeDescriptions()
                        .stream()
                        .map(CoffeeDescriptionMapper::toEntity)
                        .collect(Collectors.toList());
        coffee.setCoffeeDescriptions(coffeeDescriptions);

        return coffee;
    }

    public static CoffeeDTO toDTO(Coffee coffee) {
        CoffeeDTO coffeeDTO = new CoffeeDTO();

        coffeeDTO.setId(coffee.getId());
        coffeeDTO.setName(coffee.getName());

        List<CoffeeDescriptionDTO> coffeeDescriptions =
                coffee.getCoffeeDescriptions()
                        .stream()
                        .map(CoffeeDescriptionMapper::toDTO)
                        .collect(Collectors.toList());
        coffeeDTO.setCoffeeDescriptions(coffeeDescriptions);

        return coffeeDTO;
    }

}
