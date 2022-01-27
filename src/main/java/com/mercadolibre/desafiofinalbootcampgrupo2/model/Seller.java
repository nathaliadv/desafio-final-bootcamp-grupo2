package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Seller extends User {

    @ManyToOne
    private Advertising advertising;
}
