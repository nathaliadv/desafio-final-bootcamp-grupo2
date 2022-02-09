package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingDTO {

    private String observacao;
    private long purchaseOrderId;
    private long vehicleId;
}
