package com.afirma.test.bookings.utils;

import com.afirma.test.bookings.common.constants.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateUtils {

    public static LocalDate stringToLocalDate(String date){
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(Date.PATTERN));
        }catch (DateTimeParseException ex){
            return null;
        }
    }

    public static Boolean rangesOverlap(LocalDate startA, LocalDate endA, LocalDate startB, LocalDate endB) {
        return !startA.isAfter(endB) && !startB.isAfter(endA);
    }

}
