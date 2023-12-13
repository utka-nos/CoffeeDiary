package com.example.api.performance.service;

import com.example.dto.PerformanceDTO;
import com.example.entity.Performance;
import com.example.mapper.PerformanceMapper;
import com.example.repo.PerformanceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PerformanceService {

    @Autowired
    private PerformanceRepo performanceRepo;

    public List<PerformanceDTO> getAllPerformances() {

        List<Performance> performances = performanceRepo.findAll();

        log.info("Got performances from db. Total performances: {{}}", performances.size());

        return performances.stream()
                .map(PerformanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public PerformanceDTO addNewPerformance(PerformanceDTO newPerformanceDTO) {

        log.info("Saving new performance to database...");

        Performance savedPerformance = performanceRepo.save(PerformanceMapper.toEntity(newPerformanceDTO));

        log.info("Performance saved. Performance id: {{}}", savedPerformance.getId());

        return PerformanceMapper.toDTO(savedPerformance);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public void deletePerformance(Long id) {
        Performance performance = performanceRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        performanceRepo.delete(performance);
    }
}
