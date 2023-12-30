package com.example.api.coffeeDescription.controller;

import com.example.api.coffeeDescription.service.CoffeeDescriptionService;
import com.example.dto.CoffeeDescriptionDTO;
import com.example.entity.Performance;
import com.example.jsonviews.CoffeeDescriptionJSONView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/description")
@Slf4j
public class CoffeeDescriptionController {

    @Autowired
    private CoffeeDescriptionService coffeeDescriptionService;

    /** Добавление нового CoffeeDescription
     *
     * @param coffeeDescriptionDTO - Новый coffeeDescription
     * @return - Возвращает сохраненный в бд coffeeDescription
     */
    @PostMapping
    @JsonView(CoffeeDescriptionJSONView.Main.class)
    public ResponseEntity<CoffeeDescriptionDTO> addNewCoffeeDescription(
            @RequestBody CoffeeDescriptionDTO coffeeDescriptionDTO
    ) {
        CoffeeDescriptionDTO savedCoffeeDescriptionDTO =
                coffeeDescriptionService.saveCoffeeDescription(coffeeDescriptionDTO);

        log.info("Added new coffeeDescriptionDTO: {{}}", coffeeDescriptionDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoffeeDescriptionDTO);
    }

    /** Получение информации по конкретному coffeeDescription
     *
     * @param descriptionId - id конкретного coffeeDescription
     * @return - полную информацию по coffeeDescription
     */
    @GetMapping("/{descriptionId}")
    @JsonView(CoffeeDescriptionJSONView.Full.class)
    public ResponseEntity<CoffeeDescriptionDTO> getCoffeeDescriptionDTOById(
            @PathVariable Long descriptionId
    ) {
        CoffeeDescriptionDTO coffeeDescriptionDTO = coffeeDescriptionService.getDescriptionById(descriptionId);

        log.info("Выдается coffeeDescriptionDTO: {{}}", coffeeDescriptionDTO);

        return ResponseEntity.ok(coffeeDescriptionDTO);
    }

    /** Получение списка всех CoffeeDescriptions
     *
     * @return - список всех доступных coffeeDescription
     */
    @GetMapping("/all")
    @JsonView(CoffeeDescriptionJSONView.Main.class)
    public ResponseEntity<List<CoffeeDescriptionDTO>> getAllCoffeeDescription(){
        log.debug("Getting all coffeeDescription");

        List<CoffeeDescriptionDTO> coffeeDescriptionDTOs = coffeeDescriptionService.getAllCoffeeDescriptions();

        return ResponseEntity.status(HttpStatus.OK).body(coffeeDescriptionDTOs);
    }

    @GetMapping("/performances")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Set<Performance>> getAllPerformances() {
        return ResponseEntity.ok(Arrays.stream(Performance.values()).collect(Collectors.toSet()));
    }
}
