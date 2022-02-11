package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {

    private String licensePlate;
    private String vehicleModel;
    private LocalDate maintenanceDate;
    private double mileage;
    private Long warehouseId;
    private Long representativeId;

}
