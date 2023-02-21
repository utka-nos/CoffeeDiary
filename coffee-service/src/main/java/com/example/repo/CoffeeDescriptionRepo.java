package com.example.repo;

import com.example.entity.CoffeeDescription;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeDescriptionRepo extends CrudRepository<CoffeeDescription, Long> {
}
