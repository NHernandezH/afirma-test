package com.afirma.test.bookings.common.exception;

public class BookingNotFoundException extends DomainException{
    public BookingNotFoundException(String message) {
        super(message);
    }

    public static BookingNotFoundException of(Long id){
        String message = String.format("La reservacion con id %s no existe en el sistema",id);
        return new BookingNotFoundException(message);
    }
}
