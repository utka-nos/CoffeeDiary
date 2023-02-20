package com.example.api.coffee.service;

import com.example.dto.CoffeeDTO;
import com.example.entity.Coffee;
import com.example.mapper.CoffeeMapper;
import com.example.repo.CoffeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoffeeService {

    @Autowired
    private CoffeeRepo coffeeRepo;

    /*@Transactional(isolation = Isolation.SERIALIZABLE)
    public CoffeeDTO addNewCoffee(CoffeeDTO coffeeDTO) {

        //Coffee coffee = CoffeeMapper.toEntity(coffeeDTO);
        //Coffee savedCoffee = coffeeRepo.save(coffee);

        //return CoffeeMapper.toDTO(savedCoffee);
    }*/

    /*public List<CoffeeDTO> getAllCoffees() {

        List<Coffee> allCoffees = coffeeRepo.findAll();

        return allCoffees.stream()
                //.map(CoffeeMapper::toDTO)
                //.collect(Collectors.toList());
    }*/

}
