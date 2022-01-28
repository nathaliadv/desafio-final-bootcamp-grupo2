package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {

    private double minimumTemperature;
    private double currentTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;
    private Long advertsimentId;
}
