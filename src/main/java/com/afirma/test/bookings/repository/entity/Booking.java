package com.afirma.test.bookings.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@Entity
@Table(name = "BOOKING")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="CLIENT_ID")
    private Client client;

    @ManyToOne
    @JoinColumn(name="ROOM_ID")
    private Room room;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "STATUS")
    private String status;
}
