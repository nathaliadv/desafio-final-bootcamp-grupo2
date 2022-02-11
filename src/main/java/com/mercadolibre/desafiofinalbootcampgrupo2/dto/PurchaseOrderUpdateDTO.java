package com.mercadolibre.desafiofinalbootcampgrupo2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderUpdateDTO {

    @NotNull(message = "Status can not be null.")
    @NotEmpty(message = "Status can not be empty.")
    private String status;
    @Valid
    @NotEmpty(message = "Product list can not be empty.")
    private List<ProductDTO> products;
}
