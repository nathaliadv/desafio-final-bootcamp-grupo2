package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "advertising")
public class Advertising {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal price;
}
