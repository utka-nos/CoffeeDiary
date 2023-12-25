package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coffee_descriptions")
public class CoffeeDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffee_descriptions_id_seq")
    @SequenceGenerator(
            name = "coffee_descriptions_id_seq",
            sequenceName = "coffee_descriptions_id_seq",
            schema = "public",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private String description;

    //@Enumerated(EnumType.ORDINAL) - set type of column to `smallint`
    //EnumType.STRING - makes column type to varchar
    @Enumerated(EnumType.STRING)
    private Performance performance;

    @Column(name = "`value`")
    private String value;

}
