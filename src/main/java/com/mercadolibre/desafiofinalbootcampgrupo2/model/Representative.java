package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "representative")
public class Representative extends User{

    @OneToMany
    private List<Section> section;


}
