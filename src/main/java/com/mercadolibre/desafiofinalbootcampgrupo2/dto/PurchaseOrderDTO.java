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
public class PurchaseOrderDTO {
    private String status;
    private Long buyerId;
    private List<ProductDTO> products;
}
