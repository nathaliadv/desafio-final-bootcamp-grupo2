package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchResponseDTO {

    private Long id;
    private Long advertising;
    private double currentTemperature;
    private double minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;
    private LocalDate expirationDate;
    private Long inboundOrder;

    public BatchResponseDTO(Batch batch, Long inboundOrderCode) {
        this.id = batch.getId();
        this.currentTemperature = batch.getCurrentTemperature();
        this.minimumTemperature = batch.getMinimumTemperature();
        this.initialQuantity = batch.getInitialQuantity();
        this.currentQuantity = batch.getCurrentQuantity();
        this.manufacturingDate = batch.getManufacturingDate();
        this.manufacturingTime = batch.getManufacturingTime();
        this.expirationDate = batch.getExpirationDate();
        this.advertising = batch.getAdvertising().getId();
        this.inboundOrder = inboundOrderCode;
    }
}
