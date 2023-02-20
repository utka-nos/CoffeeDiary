package com.example.dto;

import com.example.entity.Performance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeDescriptionDTO {

    private Long id;

    private String name;

    private String description;

    private PerformanceDTO performanceDTO;

}
