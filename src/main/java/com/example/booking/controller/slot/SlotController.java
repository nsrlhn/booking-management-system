package com.example.booking.controller.slot;

import com.example.booking.model.slot.Slot;
import com.example.booking.request.slot.SlotSaveRequest;
import com.example.booking.request.slot.SlotUpdateRequest;
import com.example.booking.service.slot.SlotCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"slot"})
@Tag(name = "slot")
public class SlotController {

    private final SlotCrudService crudService;

    @GetMapping(value = "{id}")
    @Operation(summary = "Find Slot by Id", description = "Throw error if slot is not found.")
    public Slot find(@PathVariable Long id) {
        return crudService.find(id);
    }

    @PostMapping
    @Operation(summary = "Save Slot", description = "Throw error if slot is already exist.")
    public Slot save(@Valid @RequestBody SlotSaveRequest request) {
        return crudService.save(request);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update slot", description = "Throw error if slot is booked.")
    public Slot update(@PathVariable Long id, @Valid @RequestBody SlotUpdateRequest request) {
        return crudService.update(id, request);
    }
}
