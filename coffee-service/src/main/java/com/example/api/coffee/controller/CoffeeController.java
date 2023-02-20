package com.example.api.coffee.controller;

import com.example.api.coffee.service.CoffeeService;
import com.example.dto.CoffeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    /*@PostMapping
    public ResponseEntity<CoffeeDTO> addNewCoffee(
            @RequestBody CoffeeDTO coffeeDTO
    ) {
        CoffeeDTO savedCoffeeDTO = coffeeService.addNewCoffee(coffeeDTO);
        return ResponseEntity.ok(savedCoffeeDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CoffeeDTO>> getAllCoffees() {
        List<CoffeeDTO> coffeesDTO = coffeeService.getAllCoffees();
        return ResponseEntity.ok(coffeesDTO);
    }*/

}
