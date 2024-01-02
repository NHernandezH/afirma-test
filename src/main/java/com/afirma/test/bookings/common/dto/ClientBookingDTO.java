package com.afirma.test.bookings.common.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientBookingDTO {

    private String roomNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalNights;
}
