package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDTO {

    private LocalDate creationDate;
    private SectionDTO section;
    private List<BatchDTO> batchs;
    private long representative;
}
