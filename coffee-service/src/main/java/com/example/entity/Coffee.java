package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coffee")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffee_id_seq")
    @SequenceGenerator(
            name = "coffee_id_seq",
            sequenceName = "coffee_id_seq",
            schema = "public",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "coffee_id")
    private List<CoffeeDescription> coffeeDescriptions = new ArrayList<>();

    @Column(name = "author_id", nullable = false)
    private Long authorId;

}
