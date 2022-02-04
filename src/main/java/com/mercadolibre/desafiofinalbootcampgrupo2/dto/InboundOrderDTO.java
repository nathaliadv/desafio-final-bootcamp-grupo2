package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundOrderDTO {
    private Long inboundOrderCode;
    @NotNull(message = "CreationDate can not be null")
    private LocalDate creationDate;
    @NotNull(message = "Section can not be null")
    @Valid
    private SectionDTO section;
    @NotNull(message = "Batchs can not be null")
    private List<BatchDTO> batchs;
    @NotNull(message = "Representative can not be null")
    private Long representative;

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
