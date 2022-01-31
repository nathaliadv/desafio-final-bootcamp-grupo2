package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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
    private LocalTime manufacturingTime;

    @ManyToOne
    private Advertising advertising;

    @ManyToOne(cascade = CascadeType.ALL)
    private InboundOrder inboundOrder;
}
