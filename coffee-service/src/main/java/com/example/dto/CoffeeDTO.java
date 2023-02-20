package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeDTO {

    private Long id;

    private String name;

    private List<CoffeeDescriptionDTO> coffeeDescriptions = new ArrayList<>();
}
