package com.example.booking.controller.slot;

import com.example.booking.model.slot.Slot;
import com.example.booking.request.slot.SlotBookRequest;
import com.example.booking.service.slot.SlotBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"slot"})
@Tag(name = "booking")
public class BookingController {

    private final SlotBookService bookService;

    @PutMapping("book/{id}")
    @Operation(summary = "Book slot")
    public Slot book(@PathVariable Long id, @Valid @RequestBody SlotBookRequest request) {
        return bookService.book(id, request);
    }
}
