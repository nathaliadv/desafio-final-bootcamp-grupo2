package com.mercadolibre.desafiofinalbootcampgrupo2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double volume;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Advertising> advertisings;

    @ManyToOne
    private ProductType productType;
}
