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
public class UserDTO {
    private Long id;
    @NotEmpty
    @NotNull(message = "Name can not be null")
    private String name;
    @NotEmpty
    @NotNull(message = "Email can not be null")
    private String email;
    private String userName;
    private boolean isEnabled;
    private boolean isCredentialsNonExpired;

}
