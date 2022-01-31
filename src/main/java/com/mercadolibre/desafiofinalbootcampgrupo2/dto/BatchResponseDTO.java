package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchResponseDTO {

    private Long advertisingCode;
    private double currentTemperature;
    private double minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;
    private LocalDate expirationDate;
    private Long inboundorderCode;

    public BatchResponseDTO(Batch batch) {
        this.currentTemperature = batch.getCurrentTemperature();
        this.minimumTemperature = batch.getMinimumTemperature();
        this.initialQuantity = batch.getInitialQuantity();
        this.currentQuantity = batch.getCurrentQuantity();
        this.manufacturingDate = batch.getManufacturingDate();
        this.manufacturingTime = batch.getManufacturingTime();
        this.expirationDate = batch.getExpirationDate();
        this.advertisingCode = batch.getAdvertising().getId();
        this.inboundorderCode = batch.getInboundOrder().getId();
    }
}
