package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDTO {

    private Long inboundOrderCode;
    private LocalDate creationDate;
    private SectionDTO section;
    private List<BatchDTO> batchs;
    private long representative;

    public InboundOrderDTO(InboundOrder inboundOrder) {
        this.inboundOrderCode = inboundOrder.getId();
        this.creationDate = inboundOrder.getCreationDate();
        this.section = SectionDTO.builder()
                .sectionCode(inboundOrder.getSection().getId())
                .warehouseCode(inboundOrder.getSection().getWarehouse().getId())
                .build();
        this.batchs = inboundOrder.getBatchs()
                .stream()
                .map(batch -> new BatchDTO(batch))
                .collect(Collectors.toList());
        this.representative = inboundOrder.getSection().getRepresentative().getId();
    }


}
