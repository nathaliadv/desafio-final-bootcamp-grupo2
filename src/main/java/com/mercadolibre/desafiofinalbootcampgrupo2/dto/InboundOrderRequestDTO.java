package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundOrderRequestDTO {

    @NotNull(message = "CreationDate can not be null")
    private LocalDate creationDate;
    @NotNull(message = "Section can not be null")
    @Valid
    private SectionDTO section;
    @NotNull(message = "Batchs can not be null")
    private List<BatchDTO> batchs;

}
