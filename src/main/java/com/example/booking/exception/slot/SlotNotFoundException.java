package com.example.booking.exception.slot;

import javax.persistence.EntityNotFoundException;

public class SlotNotFoundException extends EntityNotFoundException {

    public SlotNotFoundException(Long id) {
        super(String.format("Slot(id=%d) Not Found", id));
    }
}
