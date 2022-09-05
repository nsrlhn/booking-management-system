package com.example.booking.service.booking;

import com.example.booking.model.booking.Booking;
import com.example.booking.model.company.Company;
import com.example.booking.repository.booking.BookingRepository;
import com.example.booking.request.booking.BookingRequest;
import com.example.booking.service.availability.CompanyAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class BookingCUDService {

    private final BookingRepository repository;
    private final CompanyAvailabilityService availabilityService;

    public Booking reserveOneHour(BookingRequest request) {
        LocalDateTime startDateTime = LocalDateTime.of(request.getDate(), LocalTime.of(request.getHour(), 0));
        LocalDateTime endDateTime = LocalDateTime.of(request.getDate(), LocalTime.of(request.getHour(), 0).plusHours(1));

        // Check & Get
        Company company = availabilityService.getCompanyIfAvailable(request.getCompanyId(), startDateTime, endDateTime);

        // Prepare
        Booking booking = new Booking();
        booking.setCompany(company);
        booking.setStart(startDateTime);
        booking.setEnd(endDateTime);
        booking.setCustomerName(request.getCustomerName());

        // Save
        return repository.save(booking);
    }
}
