package com.mercadolibre.desafiofinalbootcampgrupo2.model;


import com.mercadolibre.desafiofinalbootcampgrupo2.util.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private double volume;
    @OneToMany(mappedBy = "product")
    private List<Advertising> advertisings;
    private ProductType productTypes;
}
