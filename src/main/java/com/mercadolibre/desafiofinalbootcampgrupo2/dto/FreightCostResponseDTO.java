package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreightCostResponseDTO {
    private BigDecimal shippingCost;
}
