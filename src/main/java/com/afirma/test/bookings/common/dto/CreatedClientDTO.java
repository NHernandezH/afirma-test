package com.afirma.test.bookings.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedClientDTO extends ClientDTO{

    private Long id;
    private LocalDate registerDate;
}
