package com.example.repo;

import com.example.entity.Performance;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepo extends CrudRepository<Performance, Long> {
}
