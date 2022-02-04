package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDTO {

    @NotNull(message = "SectionCode can not be null")
    private Long sectionCode;
    @NotNull(message = "WarehouseCode can not be null")
    private Long warehouseCode;
}
