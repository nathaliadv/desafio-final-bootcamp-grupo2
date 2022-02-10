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
public class AdvertisingResponseDTO {

    private Long id;
    private String productName;
    private String productType;
    private String description;
    private BigDecimal price;
    private Boolean freeShipping;
    private Integer quantity;

    public AdvertisingResponseDTO(Advertising advertising) {
        this.id = advertising.getId();
        this.productName = advertising.getProduct().getName();
        this.productType = advertising.getProduct().getProductType().getType();
        this.description = advertising.getDescription();
        this.price = advertising.getPrice();
        this.freeShipping = advertising.getFreeShipping();
    }
}