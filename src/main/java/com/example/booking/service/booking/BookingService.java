package com.example.booking.service.booking;

import com.example.booking.exception.booking.BookingDurationException;
import com.example.booking.exception.booking.CompanyIsNotAvailable;
import com.example.booking.exception.booking.CompanyIsNotWorking;
import com.example.booking.model.booking.Booking;
import com.example.booking.model.company.Company;
import com.example.booking.model.company.WorkingHour;
import com.example.booking.repository.booking.BookingRepository;
import com.example.booking.request.booking.BookingRequest;
import com.example.booking.service.company.WorkingHourCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;
    private final WorkingHourCrudService workingHourCrudService;

    public Booking reserveOneHour(BookingRequest request) {
        LocalDateTime startDateTime = LocalDateTime.of(request.getDate(), LocalTime.of(request.getHour(), 0));
        LocalDateTime endDateTime = LocalDateTime.of(request.getDate(), LocalTime.of(request.getHour(), 0).plusHours(1));

        // Check & Get
        Company company = this.getCompanyIfAvailable(request.getCompanyId(), startDateTime, endDateTime);

        // Prepare
        Booking booking = new Booking();
        booking.setCompany(company);
        booking.setStart(startDateTime);
        booking.setEnd(endDateTime);
        booking.setCustomerName(request.getCustomerName());

        // Save
        return repository.save(booking);
    }

    public Company getCompanyIfAvailable(Long companyId, LocalDateTime start, LocalDateTime end) {
        // Check: Duration
        if (!start.toLocalDate().equals(end.toLocalDate())) {
            throw new BookingDurationException();
        }

        // Get
        WorkingHour workingHour = workingHourCrudService.find(companyId, start.getDayOfWeek());

        // Check: If company is working
        if (workingHour.getStart().isAfter(start.toLocalTime()) || workingHour.getEnd().isBefore(end.toLocalTime())) {
            throw new CompanyIsNotWorking(companyId, start);
        }

        // Check: If company is available
        if (repository.numberOfOverlap(companyId, start, end) != 0) {
            throw new CompanyIsNotAvailable(companyId, start);
        }

        return workingHour.getId().getCompany();
    }
}
