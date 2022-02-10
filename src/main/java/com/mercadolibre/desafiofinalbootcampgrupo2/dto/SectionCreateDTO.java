package com.mercadolibre.desafiofinalbootcampgrupo2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionCreateDTO {

    @NotEmpty(message = "Field nameSection cannot be blank")
    @Length(min = 3, max = 100, message = "The nameSection must contain between 5 100 characters to be valid")
    private String nameSection;
    @NotNull(message = "Capacity can not be null")
    private double capacity;
    @NotNull
    private double temperature;
    @NotEmpty(message = "Field description cannot be blank")
    @Length(min = 5, max = 100, message = "The description must contain between 5 and 100 characters to be valid")
    private String description;
    @NotNull(message = "Warehouse ID cannot be null or is blank")
    private Long warehouseId;
    @NotNull(message = "Product type cannot be null or is blank")
    private Long productTypeId;
    @NotNull(message = "Representative ID cannot be null or is blank")
    private Long representativeId;

}
