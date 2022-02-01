package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponseDTO {

    private Long productCode;
    private String productName;
    private Long sectionCode;
    private Long warehouseCode;
    private Long batchCode;
    private int currentQuantity;
    private LocalDate expirationDate;
}