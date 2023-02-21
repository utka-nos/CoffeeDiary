package com.example.dto;

import com.example.entity.Performance;
import com.example.jsonviews.CoffeeJSONView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeDescriptionDTO {

    private Long id;

    @JsonView(CoffeeJSONView.Main.class)
    private String name;

    private String description;

    private PerformanceDTO performance;

}
