package com.mercadolibre.desafiofinalbootcampgrupo2.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    private Long buyerId;
    private List<ProductDTO> products;
}
