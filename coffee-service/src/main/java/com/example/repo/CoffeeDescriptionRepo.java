package com.example.repo;

import com.example.entity.CoffeeDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeDescriptionRepo extends JpaRepository<CoffeeDescription, Long> {
}
