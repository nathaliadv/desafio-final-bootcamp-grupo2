package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Boolean validPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
