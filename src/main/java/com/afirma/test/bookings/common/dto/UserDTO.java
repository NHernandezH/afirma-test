package com.afirma.test.bookings.common.dto;

import lombok.*;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String password;
    private String role;
}
