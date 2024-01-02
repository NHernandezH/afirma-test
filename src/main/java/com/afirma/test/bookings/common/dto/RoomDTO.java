package com.afirma.test.bookings.common.dto;

import com.afirma.test.bookings.common.enums.RoomType;
import com.afirma.test.bookings.common.validators.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    @NotEmpty(message = "El numero de habitacion es obligatorio")
    private String number;
    @NotEmpty(message = "El tipo de habitacion es obligatorio")
    @EnumValidator.EnumValidation(enumClass = RoomType.class,
            message = "El tipo de cuarto no es valido, solo se permiten (INDIVIDUAL, DUPLEX, TRIPLE, SUITE)")
    private String type;
    private String description;
    @NotNull(message = "La capacidad de personas es obligatorio")
    @Min(value = 1, message = "El numero de personas debe ser mayor a cero")
    private Integer capacity;
    @NotNull(message = "El precio por noche es obligatorio")
    @Min(value = 1, message = "El precio por noche debe ser mayor a cero")
    private BigDecimal pricePerNight;
}
