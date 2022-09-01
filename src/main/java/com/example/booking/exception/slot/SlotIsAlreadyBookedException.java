package com.example.booking.exception.slot;

public class SlotIsAlreadyBookedException extends RuntimeException {

    public SlotIsAlreadyBookedException(Long id) {
        super(String.format("Slot(id=%d) is Already Booked", id));
    }
}
