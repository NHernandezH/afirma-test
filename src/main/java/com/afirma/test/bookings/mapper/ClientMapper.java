package com.afirma.test.bookings.mapper;

import com.afirma.test.bookings.common.dto.*;
import com.afirma.test.bookings.repository.entity.Booking;
import com.afirma.test.bookings.repository.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "registerDate", expression = "java(java.time.LocalDate.now())")
    Client dtoToEntity(ClientDTO client);
    CreatedClientDTO entityToDto(Client client);


    @Mapping(source = "bookings", target = "bookings", qualifiedByName = "bookingsToDto")
    CreatedClientDetailDTO entityToDetailDto(Client client);

    @Named("bookingsToDto")
    static List<ClientBookingDTO> bookingsToDto(List<Booking> bookings){
        return bookings.stream()
                .map(BookingMapper.INSTANCE::entityToClientDto)
                .collect(Collectors.toList());
    }

    void update(@MappingTarget Client client, ClientDTO clientDTO);

    default List<CreatedClientDTO> entityToDto(List<Client> clients){
        return clients.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
