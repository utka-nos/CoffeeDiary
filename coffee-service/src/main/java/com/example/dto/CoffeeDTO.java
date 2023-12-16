package com.example.dto;

import com.example.UserDTO;
import com.example.jsonviews.CoffeeJSONView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeDTO {

    @JsonView(CoffeeJSONView.Short.class)
    private Long id;

    @JsonView(CoffeeJSONView.Short.class)
    private String name;

    @JsonView(CoffeeJSONView.Full.class)
    private List<CoffeeDescriptionDTO> coffeeDescriptions = new ArrayList<>();

    @JsonView(CoffeeJSONView.Main.class)
    private Long authorId;
}
