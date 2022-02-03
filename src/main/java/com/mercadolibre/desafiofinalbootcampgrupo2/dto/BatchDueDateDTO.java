package com.mercadolibre.desafiofinalbootcampgrupo2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchDueDateDTO {

    private Long batchId;
    private Long productId;
    private Long productTypeId;
    private LocalDate dueDate;
    private Integer quantity;
}
