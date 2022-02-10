package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisingDTO {

    private Long id;
    @NotNull(message = "ProductCode can't be null.")
    private Long productCode;
    @NotNull(message = "Description can't be null.")
    private String description;
    @NotNull(message = "Price can't be null.")
    private BigDecimal price;
    @NotNull(message = "FreeShipping can't be null.")
    private Boolean freeShipping;

    public AdvertisingDTO(Advertising advertising) {
        this.id = advertising.getId();
        this.productCode = advertising.getProduct().getId();
        this.description = advertising.getDescription();
        this.price = advertising.getPrice();
        this.freeShipping = advertising.getFreeShipping();
    }
}