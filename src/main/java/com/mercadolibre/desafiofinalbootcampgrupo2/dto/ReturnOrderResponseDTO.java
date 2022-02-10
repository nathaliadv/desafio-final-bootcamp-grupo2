package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnOrderResponseDTO {

    private Long order;

    private LocalDate date;

    private String returnStatus;

    private Long buyer;

    private List<ReturnItemDTO> returnItens;

    private String returnsCause;
}
