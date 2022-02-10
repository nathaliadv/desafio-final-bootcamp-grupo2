package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnOrderCreateDTO {

    @Valid
    @NotEmpty(message = "Itens list can not be empty.")
    private List<ReturnItemCreateDTO> itens;
    @NotNull(message = "Return's cause can not be null.")
    @NotEmpty(message = "Return's cause can not be empty.")
    private String returnCause;
}
