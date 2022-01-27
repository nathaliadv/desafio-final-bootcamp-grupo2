package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
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
    private List<Batch> batchs;
    private Representative representative;
}
