package com.afirma.test.bookings.service;

import com.afirma.test.bookings.common.dto.BookingDTO;
import com.afirma.test.bookings.common.exception.BookingNotFoundException;
import com.afirma.test.bookings.common.exception.ClientNotFoundException;
import com.afirma.test.bookings.common.exception.DatesNotAvailableException;
import com.afirma.test.bookings.common.exception.RoomNotFoundException;
import com.afirma.test.bookings.repository.BookingRepository;
import com.afirma.test.bookings.repository.ClientRepository;
import com.afirma.test.bookings.repository.RoomRepository;
import com.afirma.test.bookings.repository.entity.Booking;
import com.afirma.test.bookings.repository.entity.Client;
import com.afirma.test.bookings.repository.entity.Room;
import com.afirma.test.bookings.utils.DateUtils;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class BookingServiceTests {

    @Autowired
    private BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @Autowired
    RoomService roomService;

    @MockBean
    RoomRepository roomRepository;

    @Autowired
    ClientService clientService;

    @MockBean
    ClientRepository clientRepository;

    @Test
    public void whenDelete_thenThrowBookingNotFoundException(){
        Long id = Long.valueOf("12");

        Assertions.assertThrows(BookingNotFoundException.class, () -> {
            bookingService.delete(id);
        });
    }

    @Test
    public void whenDelete_thenDelete(){
        Long id = Long.valueOf("12");
        Booking booking = Instancio.create(Booking.class);
        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));
        bookingService.delete(id);
        verify(bookingRepository,times(1)).delete(booking);
    }

    @Test
    public void whenUpdateBookingStatus_thenThrowBookingNotFoundException(){
        Long id = Long.valueOf("12");
        String status = "PENDING";
        Assertions.assertThrows(BookingNotFoundException.class, () -> {
            bookingService.updateBookingStatus(id,status);
        });
    }

    @Test
    public void whenUpdateBookingStatus_thenUpdate(){
        Long id = Long.valueOf("12");
        String status = "PENDING";
        Booking booking = Instancio.create(Booking.class);
        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        bookingService.updateBookingStatus(id,status);
        verify(bookingRepository,times(1)).save(booking);
    }

    @Test
    public void whenFindById_thenThrowBookingNotFoundException(){
        Long id = Long.valueOf("12");
        String status = "PENDING";

        Assertions.assertThrows(BookingNotFoundException.class, () -> {
            bookingService.findById(id);
        });
    }

    @Test
    public void whenFindById_thenFind(){
        Long id = Long.valueOf("12");
        Booking booking = Instancio.create(Booking.class);
        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        bookingService.findById(id);
        verify(bookingRepository,times(1)).findById(id);
    }

    @Test
    public void whenSave_theThrowRoomNotFoundException(){
        BookingDTO booking = Instancio.create(BookingDTO.class);

        Assertions.assertThrows(RoomNotFoundException.class, () -> {
            bookingService.save(booking);
        });
    }

    @Test
    public void whenSave_theThrowDatesNotAvailableException(){
        BookingDTO booking = Instancio.create(BookingDTO.class);
        booking.setStartDate("01/01/2021");
        booking.setEndDate("03/01/2021");
        Room room = Instancio.create(Room.class);
        Booking bookingEntity = Instancio.create(Booking.class);
        bookingEntity.setStartDate(DateUtils.stringToLocalDate("02/01/2021"));
        bookingEntity.setEndDate(DateUtils.stringToLocalDate("06/01/2021"));
        room.setBookings(Arrays.asList(bookingEntity));
        when(roomRepository.findById(booking.getRoomId())).thenReturn(Optional.of(room));

        Assertions.assertThrows(DatesNotAvailableException.class, () -> {
            bookingService.save(booking);
        });
    }

    @Test
    public void whenSaveAndNotExistsBookings_theThrowClientNotFoundException(){
        BookingDTO booking = Instancio.create(BookingDTO.class);
        Room room = Instancio.create(Room.class);
        room.setBookings(new ArrayList<>());
        when(roomRepository.findById(booking.getRoomId())).thenReturn(Optional.of(room));

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            bookingService.save(booking);
        });
    }

    @Test
    public void whenSaveAndExistsBookings_theThrowClientNotFoundException(){
        BookingDTO booking = Instancio.create(BookingDTO.class);
        booking.setStartDate("01/01/2021");
        booking.setEndDate("03/01/2021");
        Room room = Instancio.create(Room.class);
        Booking bookingEntity = Instancio.create(Booking.class);
        bookingEntity.setStartDate(DateUtils.stringToLocalDate("04/01/2021"));
        bookingEntity.setEndDate(DateUtils.stringToLocalDate("06/01/2021"));
        room.setBookings(Arrays.asList(bookingEntity));
        when(roomRepository.findById(booking.getRoomId())).thenReturn(Optional.of(room));

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            bookingService.save(booking);
        });
    }

    @Test
    public void whenSaveAndExistsBookings_theSave(){
        BookingDTO booking = Instancio.create(BookingDTO.class);
        booking.setStartDate("01/01/2021");
        booking.setEndDate("03/01/2021");
        Room room = Instancio.create(Room.class);
        Booking bookingEntity = Instancio.create(Booking.class);
        bookingEntity.setStartDate(DateUtils.stringToLocalDate("04/01/2021"));
        bookingEntity.setEndDate(DateUtils.stringToLocalDate("06/01/2021"));
        room.setBookings(Arrays.asList(bookingEntity));
        when(roomRepository.findById(booking.getRoomId())).thenReturn(Optional.of(room));
        Client client = Instancio.create(Client.class);
        when(clientRepository.findById(booking.getClientId())).thenReturn(Optional.of(client));

        bookingService.save(booking);

        verify(bookingRepository,times(1))
                .save(any());
    }

}
