package com.example.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CoffeeDTO {

    private Long id;

    private String name;

    private List<CoffeeDescriptionDTO> coffeeDescriptions = new ArrayList<>();
}
