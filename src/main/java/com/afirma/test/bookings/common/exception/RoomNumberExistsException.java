package com.afirma.test.bookings.common.exception;

public class RoomNumberExistsException extends DomainException{
    public RoomNumberExistsException(String message) {
        super(message);
    }

    public static RoomNumberExistsException of(String number){
        String message = String.format("El numero de habitacion '%s' ya se encuentra registrado",number);
        return new RoomNumberExistsException(message);
    }
}
