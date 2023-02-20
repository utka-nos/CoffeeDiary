package com.example.dto;

import com.example.entity.Performance;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class CoffeeDescriptionDTO {

    private Long id;

    private String name;

    private String description;

    private PerformanceDTO performance;

}
