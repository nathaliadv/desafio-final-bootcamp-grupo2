package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tb_advertising")
public class Advertising {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal price;

    @ManyToOne
    private Seller seller;

    @OneToMany(mappedBy = "advertising")
    @JsonIgnore
    private List<Batch> batchs;

    @ManyToOne
    private Product product;

}
