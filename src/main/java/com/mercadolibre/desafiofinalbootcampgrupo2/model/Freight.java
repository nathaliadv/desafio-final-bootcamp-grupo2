package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_freight")
public class Freight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "State can not be empty")
    @NotNull(message = "State can not be null")
    private String state;

    @NotEmpty(message = "initials can not be empty")
    @NotNull(message = "initials can not be null")
    private String initial;

    @NotEmpty(message = "Capital can not be empty")
    @NotNull(message = "Capital can not be null")
    private String capital;

    @NotEmpty(message = "Region can not be empty")
    @NotNull(message = "Region can not be null")
    private String region;

    private BigDecimal shippingCost;

}
