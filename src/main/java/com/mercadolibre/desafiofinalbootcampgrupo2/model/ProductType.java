package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_type")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "productType")
    private List<Product> products;

    @OneToMany(mappedBy = "productType")
    private List<Section> sections;
}