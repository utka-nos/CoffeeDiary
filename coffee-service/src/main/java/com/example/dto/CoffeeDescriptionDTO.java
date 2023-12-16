package com.example.dto;

import com.example.entity.Performance;
import com.example.jsonviews.CoffeeDescriptionJSONView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeDescriptionDTO {

    @JsonView(CoffeeDescriptionJSONView.Main.class)
    private Long id;

    @JsonView(CoffeeDescriptionJSONView.Main.class)
    private String name;

    @JsonView(CoffeeDescriptionJSONView.Full.class)
    private String description;

    @JsonView(CoffeeDescriptionJSONView.Main.class)
    private String value;

    @JsonView(CoffeeDescriptionJSONView.Main.class)
    private Performance performance;

}
