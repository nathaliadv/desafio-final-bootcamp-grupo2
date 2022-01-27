package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
//TODO tipo de estrategia
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Serializable {
    @Id
    //TODO verificar para gerar auto increment
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;

}
