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
public class ReturnItemDTO {

    @NotNull(message = "Return item can not be null.")
    private Long returnItemId;
    @NotNull(message = "Quantity can not be null.")
    private Integer quantity;
}
