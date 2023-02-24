package com.example.mapper;

import com.example.dto.CoffeeDTO;
import com.example.dto.CoffeeDescriptionDTO;
import com.example.dto.PerformanceDTO;
import com.example.entity.Coffee;
import com.example.entity.CoffeeDescription;
import com.example.entity.Performance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMapperTest {

    Performance perf1 = new Performance(1L, "scale");
    Performance perf2 = new Performance(2L, "percent");

    CoffeeDescription coffeeDescription0 = new CoffeeDescription();
    CoffeeDescription coffeeDescription1 = new CoffeeDescription(1L, "taste", "good", "10",perf1);
    CoffeeDescription coffeeDescription2 = new CoffeeDescription(2L, "bitter", "so-so", "50" ,perf2);
    CoffeeDescription coffeeDescription3 = new CoffeeDescription(3L, "sweet", "", "10" ,perf1);

    PerformanceDTO perfDTO1 = new PerformanceDTO(1L, "scale");
    PerformanceDTO perfDTO2 = new PerformanceDTO(2L, "percent");

    CoffeeDescriptionDTO coffeeDescriptionDTO0 = new CoffeeDescriptionDTO();
    CoffeeDescriptionDTO coffeeDescriptionDTO1 = new CoffeeDescriptionDTO(1L, "taste", "good", "10", perfDTO1);
    CoffeeDescriptionDTO coffeeDescriptionDTO2 = new CoffeeDescriptionDTO(2L, "bitter", "so-so", "50", perfDTO2);
    CoffeeDescriptionDTO coffeeDescriptionDTO3 = new CoffeeDescriptionDTO(3L, "sweet", "", "10" ,perfDTO1);

    @Test
    public void onlySimpleFieldsValidTest() {
        Coffee coffee = new Coffee(2L, "kolumbia", new ArrayList<>(), null);
        CoffeeDTO coffeeDTO = new CoffeeDTO(2L, "kolumbia", new ArrayList<>(), null);

        Assertions.assertEquals(coffee, CoffeeMapper.toEntity(coffeeDTO));
        Assertions.assertEquals(coffeeDTO, CoffeeMapper.toDTO(coffee));
    }

    @Test
    public void allFieldsValidTest() {

        Coffee coffee = new Coffee(
                1L,
                "brazil",
                List.of(coffeeDescription0, coffeeDescription1, coffeeDescription2, coffeeDescription3),
                null
        );

        CoffeeDTO coffeeDTO = new CoffeeDTO(
                1L,
                "brazil",
                List.of(coffeeDescriptionDTO0, coffeeDescriptionDTO1, coffeeDescriptionDTO2, coffeeDescriptionDTO3),
                null
        );

        Assertions.assertEquals(coffee, CoffeeMapper.toEntity(coffeeDTO));
        Assertions.assertEquals(coffeeDTO, CoffeeMapper.toDTO(coffee));
    }

    @Test
    public void simpleFieldsInvalidTest() {

    }

}