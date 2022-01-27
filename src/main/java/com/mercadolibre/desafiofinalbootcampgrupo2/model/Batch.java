package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "batch")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double minimumTemperature;
    private double currentTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingDateTime;
    @ManyToOne
    private InboundOrder order;
    @ManyToOne
    private Advertising advertising;
}
