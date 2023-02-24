package com.example.mapper;

import com.example.dto.CoffeeDescriptionDTO;
import com.example.entity.CoffeeDescription;

public class CoffeeDescriptionMapper{


    public static CoffeeDescription toEntity(CoffeeDescriptionDTO coffeeDescriptionDTO) {
        CoffeeDescription coffeeDescription = new CoffeeDescription();

        coffeeDescription.setDescription(coffeeDescriptionDTO.getDescription());
        coffeeDescription.setId(coffeeDescriptionDTO.getId());
        coffeeDescription.setName(coffeeDescriptionDTO.getName());
        coffeeDescription.setValue(coffeeDescriptionDTO.getValue());
        if (coffeeDescriptionDTO.getPerformance() != null) {
            coffeeDescription.setPerformance(PerformanceMapper.toEntity(coffeeDescriptionDTO.getPerformance()));
        }

        return coffeeDescription;
    }


    public static CoffeeDescriptionDTO toDTO(CoffeeDescription coffeeDescription) {
        CoffeeDescriptionDTO coffeeDescriptionDTO = new CoffeeDescriptionDTO();

        coffeeDescriptionDTO.setDescription(coffeeDescription.getDescription());
        coffeeDescriptionDTO.setId(coffeeDescription.getId());
        coffeeDescriptionDTO.setName(coffeeDescription.getName());
        coffeeDescriptionDTO.setValue(coffeeDescription.getValue());
        if (coffeeDescription.getPerformance() != null) {
            coffeeDescriptionDTO.setPerformance(PerformanceMapper.toDTO(coffeeDescription.getPerformance()));
        }

        return coffeeDescriptionDTO;
    }
}
