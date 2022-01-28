package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "seller")
public class Seller extends User {

    @OneToMany(mappedBy = "seller")
    private List<Advertising> advertising;
}
