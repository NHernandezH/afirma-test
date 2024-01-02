package com.afirma.test.bookings.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@Entity
@Table(name = "CLIENT")
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "REGISTER_DATE")
    private LocalDate registerDate;

    @OneToMany(mappedBy = "client")
    private List<Booking> bookings;

}
