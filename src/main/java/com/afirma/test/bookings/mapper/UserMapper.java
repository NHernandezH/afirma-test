package com.afirma.test.bookings.mapper;

import com.afirma.test.bookings.common.dto.UserDTO;
import com.afirma.test.bookings.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToEntity(UserDTO user);
}
