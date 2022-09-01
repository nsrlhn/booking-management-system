package com.example.booking.exception.slot;

public class SlotIsPast extends RuntimeException {

    public SlotIsPast(Long id) {
        super(String.format("Slot(id=%d) is Past", id));
    }
}

