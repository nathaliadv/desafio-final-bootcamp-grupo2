package com.mercadolibre.desafiofinalbootcampgrupo2.dto;


import com.mercadolibre.desafiofinalbootcampgrupo2.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTypeDTO {
    private String type;
}
