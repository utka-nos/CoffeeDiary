package com.example.dto;

import com.example.jsonviews.PerformanceJSONView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDTO {

    @JsonView(PerformanceJSONView.Main.class)
    private Long id;

    @JsonView(PerformanceJSONView.Full.class)
    private String name;

}
