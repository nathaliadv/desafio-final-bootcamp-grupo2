package com.mercadolibre.desafiofinalbootcampgrupo2.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double volume;

    @OneToMany(mappedBy = "product")
    private List<Advertising> advertisings;

    @ManyToOne
    private ProductType productType;
}
