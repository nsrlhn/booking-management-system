package com.example.booking.exception.slot;

import java.time.LocalDate;

public class SlotIsAlreadyAvailableException extends RuntimeException {

    public SlotIsAlreadyAvailableException(Long customerId, LocalDate date, Integer hour) {
        super(String.format("Slot(customerId=%d,date=%s,hour=%d) is Already Available", customerId, date, hour));
    }
}
