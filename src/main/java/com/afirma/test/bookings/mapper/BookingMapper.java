package com.afirma.test.bookings.mapper;

import com.afirma.test.bookings.common.dto.ClientBookingDTO;
import com.afirma.test.bookings.common.dto.CreatedBookingDTO;
import com.afirma.test.bookings.repository.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "room.number", target = "roomNumber")
    ClientBookingDTO entityToClientDto(Booking booking);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "total", target = "totalAmount")
    CreatedBookingDTO entityToDto(Booking booking);
}
