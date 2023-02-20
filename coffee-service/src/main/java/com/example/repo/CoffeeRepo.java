package com.example.repo;

import com.example.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepo extends JpaRepository<Coffee, Long> {



}
