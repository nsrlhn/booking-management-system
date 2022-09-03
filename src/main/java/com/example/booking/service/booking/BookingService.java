package com.example.booking.service.booking;

import com.example.booking.exception.booking.CompanyIsNotAvailable;
import com.example.booking.exception.booking.CompanyIsNotWorkingAtThatTime;
import com.example.booking.model.booking.Booking;
import com.example.booking.model.company.Company;
import com.example.booking.model.company.WorkingHour;
import com.example.booking.repository.booking.BookingRepository;
import com.example.booking.request.booking.BookingRequest;
import com.example.booking.service.company.CompanyCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;
    private final CompanyCrudService companyCrudService;

    public Booking reserveOneHour(BookingRequest request) {
        LocalTime startTime = LocalTime.of(request.getHour(), 0);
        LocalTime endTime = startTime.plusHours(1);
        LocalDateTime startDateTime = LocalDateTime.of(request.getDate(), startTime);
        LocalDateTime endDateTime = LocalDateTime.of(request.getDate(), endTime);

        // Check & Get
        Company company = companyCrudService.find(request.getCompanyId());
        Optional<WorkingHour> workingHour = company.getWorkingHours()
                .stream()
                .filter(wh -> wh.getId().getDayOfWeek().equals(request.getDate().getDayOfWeek()))
                .findFirst();

        // Check: If company is working
        if (workingHour.isEmpty() || workingHour.get().getStart().isAfter(startTime) || workingHour.get().getEnd().isBefore(endTime)) {
            throw new CompanyIsNotWorkingAtThatTime(request.getCompanyId(), startDateTime);
        }

        // Check: If company is available
        if (repository.numberOfOverlap(company.getId(), startDateTime, endDateTime) != 0) {
            throw new CompanyIsNotAvailable(request.getCompanyId(), startDateTime);
        }

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
