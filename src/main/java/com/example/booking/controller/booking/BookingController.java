package com.example.booking.controller.booking;

import com.example.booking.model.booking.Booking;
import com.example.booking.request.booking.BookingRequest;
import com.example.booking.service.booking.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"booking"})
@Tag(name = "booking")
public class BookingController {

    private final BookingService bookingService;

    @PutMapping("reserveAnHour")
    @Operation(summary = "Reserve an hour")
    public Booking reserveOneHour(@Valid @RequestBody BookingRequest request) {
        return bookingService.reserveOneHour(request);
    }
}
