package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller extends User {

    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private List<Advertising> advertising;

}
