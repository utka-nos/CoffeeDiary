package com.example.mapper;

import com.example.dto.CoffeeDTO;
import com.example.entity.Coffee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoffeeMapperTest {

    @Test
    public void coffeeMapperValidTest() {
        Coffee coffee = new Coffee();
        CoffeeDTO coffeeDTO = new CoffeeDTO();

        coffee.setId(1L);
        coffeeDTO.setId(1L);

        String name = "kolumbia";
        coffee.setName(name);
        coffeeDTO.setName(name);

        Assertions.assertEquals(coffee, CoffeeMapper.toEntity(coffeeDTO));
        Assertions.assertEquals(coffeeDTO, CoffeeMapper.toDTO(coffee));
    }

}
