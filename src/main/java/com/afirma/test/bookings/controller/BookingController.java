package com.afirma.test.bookings.controller;

import com.afirma.test.bookings.common.dto.BookingDTO;
import com.afirma.test.bookings.common.dto.CreatedBookingDTO;
import com.afirma.test.bookings.common.dto.ClientBookingDTO;
import com.afirma.test.bookings.common.enums.BookingStatus;
import com.afirma.test.bookings.common.enums.RoomType;
import com.afirma.test.bookings.common.validators.EnumValidator;
import com.afirma.test.bookings.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@Log4j2
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<CreatedBookingDTO> save(@Valid @RequestBody BookingDTO booking){
        CreatedBookingDTO createdBooking = bookingService.save(booking);
        return ResponseEntity
                .created(URI.create(String.format("/bookings/%s",createdBooking.getId())))
                .body(createdBooking);
    }

    @GetMapping
    public ResponseEntity<List<CreatedBookingDTO>> findAll(){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedBookingDTO> findById(@PathVariable Long id){
        CreatedBookingDTO booking = bookingService.findById(id);
        return ResponseEntity.ok(booking);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id,
           @RequestParam
           @EnumValidator.EnumValidation(enumClass = BookingStatus.class,
                message = "El status, solo se permiten (PENDING, CONFIRMED, CANCELLED,  CHECK_IN, CHECK_OUT)"
           ) String status){
        bookingService.updateBookingStatus(id,status.toUpperCase());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        bookingService.delete(id);
    }
}
