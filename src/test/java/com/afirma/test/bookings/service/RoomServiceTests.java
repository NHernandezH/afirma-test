package com.afirma.test.bookings.service;

import com.afirma.test.bookings.common.dto.CreatedRoomDTO;
import com.afirma.test.bookings.common.dto.RoomDTO;
import com.afirma.test.bookings.common.exception.ClientNotFoundException;
import com.afirma.test.bookings.common.exception.RoomNotFoundException;
import com.afirma.test.bookings.common.exception.RoomNumberExistsException;
import com.afirma.test.bookings.repository.RoomRepository;
import com.afirma.test.bookings.repository.entity.Room;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoomServiceTests {

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    public void whenSave_thenThrowRoomNumberExistsException(){
        RoomDTO room = Instancio.create(RoomDTO.class);
        when(roomRepository.countByNumber(room.getNumber())).thenReturn(1);

        Assertions.assertThrows(RoomNumberExistsException.class, () -> {
            roomService.save(room);
        });

    }

    @Test
    public void whenSave_thenSave(){
        RoomDTO room = Instancio.create(RoomDTO.class);
        when(roomRepository.countByNumber(room.getNumber())).thenReturn(0);
        Room roomEntity = Instancio.create(Room.class);
        when(roomRepository.save(any())).thenReturn(roomEntity);
        CreatedRoomDTO roomDTO = roomService.save(room);
        verify(roomRepository,times(1)).save(any());
        assertNotNull(roomDTO);
    }

    @Test
    public void whenFindById_theThrowRoomNotFoundException(){
        Long id = 1L;
        Assertions.assertThrows(RoomNotFoundException.class, () -> {
            roomService.findById(id);
        });

    }

    @Test
    public void whenFindById_thenFind(){
        Long id = 1L;
        Room roomEntity = Instancio.create(Room.class);
        when(roomRepository.findById(id)).thenReturn(Optional.of(roomEntity));

        RoomDTO room = roomService.findById(id);
        assertNotNull(room);

    }
}
