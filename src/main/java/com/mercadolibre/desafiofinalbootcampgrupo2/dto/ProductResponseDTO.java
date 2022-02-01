package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponseDTO {

    private Long product_id;
    private String name_product;
    private Long section_id;
    private String name_section;
    private Long warehouse_id;
    private Long batch_id;
    private int current_quantity;
    private LocalDate expirationDate;
}
