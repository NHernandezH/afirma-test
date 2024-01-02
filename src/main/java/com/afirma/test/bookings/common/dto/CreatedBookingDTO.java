package com.afirma.test.bookings.common.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Getter

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedBookingDTO extends BookingDTO {

    private Long id;
    private BigDecimal totalAmount;
    private String status;
}
