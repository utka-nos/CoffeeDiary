package com.example.repo;

import com.example.entity.CoffeeDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeDescriptionRepo extends JpaRepository<CoffeeDescription, Long> {
}
