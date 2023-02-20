package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CoffeeDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Performance performance;

}
