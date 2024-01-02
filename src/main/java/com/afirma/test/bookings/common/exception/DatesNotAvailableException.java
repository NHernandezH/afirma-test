package com.afirma.test.bookings.common.exception;

public class DatesNotAvailableException extends DomainException{
    public DatesNotAvailableException(String message) {
        super(message);
    }

    public static DatesNotAvailableException of(String startDate, String endDate, Long roomId){
        String message = String.format("La habitacion con id %s no tiene disponibilidad en las fechas del %s al %s ",
                roomId,startDate,endDate);
        return new DatesNotAvailableException(message);
    }
}
