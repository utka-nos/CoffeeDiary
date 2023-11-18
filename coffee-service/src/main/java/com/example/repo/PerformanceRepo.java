package com.example.repo;

import com.example.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepo extends JpaRepository<Performance, Long> {
}
