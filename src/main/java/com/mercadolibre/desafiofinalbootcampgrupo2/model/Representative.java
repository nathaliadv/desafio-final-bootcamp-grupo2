package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Representative extends User {

    @OneToMany(mappedBy = "representative")
    private List<Section> section;

    @OneToMany(mappedBy = "representative")
    private List<Vehicle> vehicles;

}