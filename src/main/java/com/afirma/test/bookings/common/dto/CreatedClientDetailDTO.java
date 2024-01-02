package com.afirma.test.bookings.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedClientDetailDTO extends CreatedClientDTO{

    private List<ClientBookingDTO> bookings;
}
