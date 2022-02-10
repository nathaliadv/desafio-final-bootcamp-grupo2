package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisingDTO {

    @NotNull(message = "ProductCode can't be null.")
    private Long productCode;

    @NotNull(message = "Description can't be null.")
    private String description;

    @NotNull(message = "Price can't be null.")
    private BigDecimal price;

    @NotNull(message = "FreeShipping can't be null.")
    private Boolean freeShipping;
}