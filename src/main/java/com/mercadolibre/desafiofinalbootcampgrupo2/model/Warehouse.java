package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "warehouse")
    private List<Section> sections;

    @OneToMany(mappedBy = "warehouse")
    private List<Vehicle> vehicles;
}
