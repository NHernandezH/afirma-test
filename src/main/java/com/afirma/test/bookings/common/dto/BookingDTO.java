package com.afirma.test.bookings.common.dto;

import com.afirma.test.bookings.common.validators.DateValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    @NotNull(message = "El id del cuarto es obligatorio")
    private Long roomId;
    @NotNull(message = "El id del cliente es obligatorio")
    private Long clientId;
    @NotNull(message = "La fecha de inicio es obligatoria")
    @DateValidator.DateValidation(message = "Ingrese una fecha de inicio valida con el formato dd/MM/yyyy")
    private String startDate;
    @NotNull(message = "La fecha de fin es obligatoria")
    @DateValidator.DateValidation(message = "Ingrese una fecha de fin valida con el formato dd/MM/yyyy")
    private String endDate;
    @NotNull(message = "El numero total de noches es obligatorio")
    private Integer totalNights;
}
