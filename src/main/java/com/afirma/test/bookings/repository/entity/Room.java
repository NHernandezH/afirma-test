package com.afirma.test.bookings.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@Entity
@Table(name = "ROOM")
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Column(name = "PRICE_PER_NIGHT")
    private BigDecimal pricePerNight;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

}
