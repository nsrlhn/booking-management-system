package com.example.booking.service.slot;

import com.example.booking.exception.slot.SlotIsAlreadyBookedException;
import com.example.booking.exception.slot.SlotIsNotBookedException;
import com.example.booking.exception.slot.SlotIsPast;
import com.example.booking.model.slot.Slot;
import com.example.booking.repository.slot.SlotRepository;
import com.example.booking.request.slot.SlotBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SlotBookService {

    private final SlotCrudService crudService;
    private final SlotRepository repository;

    public Slot book(Long id, SlotBookRequest request) {
        // Check & Get
        Slot slot = crudService.find(id);
        if (slot.isBooked()) {
            throw new SlotIsAlreadyBookedException(slot.getId());
        }
        if (slot.isBefore(LocalDateTime.now())) {
            throw new SlotIsPast(slot.getId());
        }

        // Prepare
        slot.setCustomerName(request.getCustomerName());

        // Save
        return repository.save(slot);
    }

    public Slot cancel(Long id) {
        // Check & Get
        Slot slot = crudService.find(id);
        if (!slot.isBooked()) {
            throw new SlotIsNotBookedException(slot.getId());
        }
        if (slot.isBefore(LocalDateTime.now())) {
            throw new SlotIsPast(slot.getId());
        }

        // Prepare
        slot.setCustomerName(null);

        // Save
        return repository.save(slot);
    }
}
