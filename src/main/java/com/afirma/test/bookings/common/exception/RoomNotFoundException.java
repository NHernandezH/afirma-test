package com.afirma.test.bookings.common.exception;

public class RoomNotFoundException extends DomainException{
    public RoomNotFoundException(String message) {
        super(message);
    }

    public static RoomNotFoundException of(Long id){
        String message = String.format("La habitacion con id %s no existe en el systema",id);
        return new RoomNotFoundException(message);
    }
}
