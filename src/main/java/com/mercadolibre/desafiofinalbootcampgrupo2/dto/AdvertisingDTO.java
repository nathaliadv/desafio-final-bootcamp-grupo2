package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisingDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
