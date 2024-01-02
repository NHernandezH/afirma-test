package com.afirma.test.bookings.common.dto;

import com.afirma.test.bookings.common.constants.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    @NotEmpty(message = "El nombre del cliente es obligatorio")
    private String name;
    @NotEmpty(message = "Los apellidos del cliente es obligatorio")
    private String lastName;
    @NotEmpty(message = "El email del cliente es obligatorio")
    @Pattern(regexp = Regex.EMAIL, message = "Ingrese un email valido")
    private String email;
    @NotEmpty(message = "El telefono del cliente es obligatorio")
    @Pattern(regexp = Regex.PHONE, message = "Ingrese un numero telefonico valido")
    private String phone;

}
