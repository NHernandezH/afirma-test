package com.afirma.test.bookings.mapper;

import com.afirma.test.bookings.common.dto.CreatedRoomDTO;
import com.afirma.test.bookings.common.dto.RoomDTO;
import com.afirma.test.bookings.repository.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(target = "number", expression = "java(room.getNumber().toUpperCase())")
    @Mapping(target = "type", expression = "java(room.getType().toUpperCase())")
    Room dtoToEntity(RoomDTO room);

    RoomDTO entityToDto(Room room);

    CreatedRoomDTO entityToCreatedDto(Room room);

    default List<RoomDTO> entityToDto(List<Room> rooms){
        return rooms.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    void update(@MappingTarget Room entity, RoomDTO dto);
}
