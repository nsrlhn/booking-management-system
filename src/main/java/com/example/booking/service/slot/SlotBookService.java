package com.example.booking.service.slot;

import com.example.booking.exception.slot.SlotIsAlreadyBookedException;
import com.example.booking.model.slot.Slot;
import com.example.booking.repository.slot.SlotRepository;
import com.example.booking.request.slot.SlotBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        // Prepare
        slot.setCustomerName(request.getCustomerName());

        // Save
        return repository.save(slot);
    }
}
