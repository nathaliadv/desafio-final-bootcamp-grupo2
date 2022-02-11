package com.mercadolibre.desafiofinalbootcampgrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {
    @NotEmpty(message = "Name can not be empty.")
    @NotNull(message = "Name can not be null")
    private String name;
    @NotEmpty(message = "Email can not be empty.")
    @NotNull(message = "Email can not be null")
    private String email;
    @NotEmpty(message = "Password can not be empty.")
    @NotNull(message = "Password can not be null")
    private String password;
}