package com.example.booking.exception.booking;

import com.example.booking.exception.CustomException;

public class BookingDurationException extends CustomException {
    
    public BookingDurationException() {
        super("Booking Should Not Overflow to the Next Day.");
    }
}
