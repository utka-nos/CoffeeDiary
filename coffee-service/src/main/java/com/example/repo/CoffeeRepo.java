package com.example.repo;

import com.example.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeRepo extends JpaRepository<Coffee, Long> {

    List<Coffee> getCoffeeByAuthorId(Long authorId);

}
