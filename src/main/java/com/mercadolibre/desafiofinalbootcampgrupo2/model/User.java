package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//TODO tipo de estrategia
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Serializable, UserDetails {

    @Id
    //TODO verificar para gerar auto increment
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
}