package com.example.mapper;

import com.example.dto.PerformanceDTO;
import com.example.entity.Performance;

public class PerformanceMapper {

    public static PerformanceDTO toDTO(Performance performance){
        PerformanceDTO performanceDTO = new PerformanceDTO();

        performanceDTO.setId(performance.getId());
        performanceDTO.setName(performance.getName());

        return performanceDTO;
    }

    public static Performance toEntity(PerformanceDTO performanceDTO) {
        Performance performance = new Performance();

        performance.setId(performanceDTO.getId());
        performance.setName(performanceDTO.getName());

        return performance;
    }

}
