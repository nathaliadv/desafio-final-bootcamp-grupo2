package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductByWarehouseDTO {

    private SectionDTO sectionDto;
    private Long productId;
    private String productName;
    private List<BatchStockDTO> listBachsStockDto;
}
