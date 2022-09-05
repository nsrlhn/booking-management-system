package com.example.booking.service.booking;

import com.example.booking.exception.booking.CompanyIsNotAvailable;
import com.example.booking.exception.booking.CompanyIsNotWorking;
import com.example.booking.exception.company.WorkingHourNotFoundException;
import com.example.booking.model.booking.Booking;
import com.example.booking.model.company.Company;
import com.example.booking.model.company.WorkingHour;
import com.example.booking.model.company.WorkingHourId;
import com.example.booking.repository.booking.BookingRepository;
import com.example.booking.repository.company.WorkingHourRepository;
import com.example.booking.request.booking.BookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;
    private final WorkingHourRepository workingHourRepository;

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
        // Get
        WorkingHour workingHour = workingHourRepository.findById(new WorkingHourId(Company.of(companyId), start.getDayOfWeek()))
                .orElseThrow(() -> new WorkingHourNotFoundException(companyId, start.getDayOfWeek()));

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
