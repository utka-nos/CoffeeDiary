package com.example.api.coffee.controller;

import com.example.api.coffee.service.CoffeeService;
import com.example.dto.CoffeeDTO;
import com.example.jsonviews.CoffeeJSONView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
@Slf4j
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @PostMapping
    @JsonView(CoffeeJSONView.Main.class)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CoffeeDTO> addNewCoffee(
            @RequestBody CoffeeDTO coffeeDTO,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getClaim("user_id");
        coffeeDTO.setAuthorId(Long.valueOf(userId));
        CoffeeDTO savedCoffeeDTO = coffeeService.addNewCoffee(coffeeDTO);

        log.info("Saved new coffeeDTO: {{}}", savedCoffeeDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoffeeDTO);
    }

    @GetMapping("/{coffeeId}")
    @JsonView(CoffeeJSONView.Full.class)
    public ResponseEntity<CoffeeDTO> getFullInfo(
            @PathVariable Long coffeeId
    ) {
        CoffeeDTO coffeeDTO = coffeeService.getCoffeeById(coffeeId);

        log.info("An attempt to get coffeeDTO: {{}}", coffeeDTO);

        return ResponseEntity.status(HttpStatus.OK).body(coffeeDTO);
    }

    @GetMapping("/admin/all")
    @JsonView(CoffeeJSONView.Short.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CoffeeDTO>> getAllCoffees() {
        List<CoffeeDTO> coffeeDTOList = coffeeService.getAllCoffees();

        log.info("Got all coffees. Total coffees: {{}}", coffeeDTOList.size());

        return ResponseEntity.ok(coffeeDTOList);
    }

    @GetMapping("/my-coffees")
    @JsonView(CoffeeJSONView.Main.class)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<CoffeeDTO>> getAllMyCoffees(
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getClaim("user_id");
        List<CoffeeDTO> coffees = coffeeService.getAllCoffeesByUserId(Long.valueOf(userId));

        return ResponseEntity.ok(coffees);
    }

}
