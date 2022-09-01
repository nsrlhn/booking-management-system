package com.example.booking.exception.slot;

public class SlotIsNotBookedException extends RuntimeException {

    public SlotIsNotBookedException(Long id) {
        super(String.format("Slot(id=%d) is Not Booked", id));
    }
}
