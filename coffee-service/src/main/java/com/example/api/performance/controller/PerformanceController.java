package com.example.api.performance.controller;

import com.example.api.performance.service.PerformanceService;
import com.example.dto.PerformanceDTO;
import com.example.jsonviews.PerformanceJSONView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performance")
@Slf4j
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/all")
    @JsonView(PerformanceJSONView.Full.class)
    public ResponseEntity<List<PerformanceDTO>> getAllPerformances() {
        List<PerformanceDTO> performanceDTOList = performanceService.getAllPerformances();

        log.info("Got {{}} performances.", performanceDTOList.size());

        return ResponseEntity.ok(performanceDTOList);
    }

    @PostMapping
    @JsonView(PerformanceJSONView.Full.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PerformanceDTO> addNewPerformance(
            @RequestBody PerformanceDTO newPerformanceDTO
    ) {
        PerformanceDTO addedPerformanceDTO = performanceService.addNewPerformance(newPerformanceDTO);

        log.info("New performance: {{}}", addedPerformanceDTO.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(addedPerformanceDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerformance(
            @PathVariable Long id
    ) {
        performanceService.deletePerformance(id);

        log.info("deleted performance with id {{}}", id);
    }

}
