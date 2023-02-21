package com.example.api.coffeeDescription.controller;

import com.example.api.coffeeDescription.service.CoffeeDescriptionService;
import com.example.dto.CoffeeDescriptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/description")
@Slf4j
public class CoffeeDescriptionController {

    @Autowired
    private CoffeeDescriptionService coffeeDescriptionService;

    @PostMapping
    public ResponseEntity<CoffeeDescriptionDTO> addNewCoffeeDescription(
            @RequestBody CoffeeDescriptionDTO coffeeDescriptionDTO
    ) {
        CoffeeDescriptionDTO savedCoffeeDescriptionDTO =
                coffeeDescriptionService.saveCoffeeDescription(coffeeDescriptionDTO);

        log.info("Added new coffeeDescriptionDTO: {{}}", coffeeDescriptionDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoffeeDescriptionDTO);
    }

    @GetMapping("/{descriptionId}")
    public ResponseEntity<CoffeeDescriptionDTO> getCoffeeDescriptionDTOById(
            @PathVariable Long descriptionId
    ) {
        CoffeeDescriptionDTO coffeeDescriptionDTO = coffeeDescriptionService.getDescriptionById(descriptionId);

        log.info("Выдается coffeeDescriptionDTO: {{}}", coffeeDescriptionDTO);

        return ResponseEntity.ok(coffeeDescriptionDTO);
    }
}
