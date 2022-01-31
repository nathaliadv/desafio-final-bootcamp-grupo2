package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchDTO {

    private double minimumTemperature;
    private double currentTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;
    private Long advertsimentId;

    public BatchDTO(Batch batch) {
        this.minimumTemperature = batch.getMinimumTemperature();
        this.currentTemperature = batch.getCurrentTemperature();
        this.initialQuantity = batch.getInitialQuantity();
        this.currentQuantity = batch.getCurrentQuantity();
        this.expirationDate = batch.getExpirationDate();
        this.manufacturingDate = batch.getManufacturingDate();
        this.manufacturingTime = batch.getManufacturingTime();
        this.advertsimentId = batch.getAdvertising().getId();
    }
}
