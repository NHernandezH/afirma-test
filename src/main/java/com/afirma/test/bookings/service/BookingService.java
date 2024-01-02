package com.afirma.test.bookings.service;

import com.afirma.test.bookings.common.dto.BookingDTO;
import com.afirma.test.bookings.common.enums.BookingStatus;
import com.afirma.test.bookings.common.dto.CreatedBookingDTO;
import com.afirma.test.bookings.common.exception.BookingNotFoundException;
import com.afirma.test.bookings.mapper.BookingMapper;
import com.afirma.test.bookings.repository.BookingRepository;
import com.afirma.test.bookings.repository.entity.Booking;
import com.afirma.test.bookings.repository.entity.Client;
import com.afirma.test.bookings.repository.entity.Room;
import com.afirma.test.bookings.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final ClientService clientService;

    public CreatedBookingDTO save(BookingDTO booking){

        Booking bookingEntity = createBooking(booking);
        bookingRepository.save(bookingEntity);
        return BookingMapper.INSTANCE.entityToDto(bookingEntity);
    }

    private Booking createBooking(BookingDTO booking){
        Room room = roomService.validateAvailability(booking.getRoomId(),booking.getStartDate(), booking.getEndDate());
        BigDecimal totalAmount = roomService.calculateTotalAmount(booking.getTotalNights(),room);
        Client client = clientService.findEntityById(booking.getClientId());
        return Booking.builder()
                .total(totalAmount)
                .room(room)
                .startDate(DateUtils.stringToLocalDate(booking.getStartDate()))
                .endDate(DateUtils.stringToLocalDate(booking.getEndDate()))
                .client(client)
                .status(BookingStatus.PENDING.name())
                .build();

    }

    public CreatedBookingDTO findById(Long id){
        Booking booking = findEntityById(id);
        return BookingMapper.INSTANCE.entityToDto(booking);
    }

    public void updateBookingStatus(Long id, String status){
        Booking booking = findEntityById(id);
        booking.setStatus(status);
        bookingRepository.save(booking);
    }

    private Booking findEntityById(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(()-> BookingNotFoundException.of(id));
    }

    public void delete(Long id){
        Booking booking = findEntityById(id);
        bookingRepository.delete(booking);
    }
}
