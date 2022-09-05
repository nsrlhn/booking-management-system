package com.example.booking.service.availability;

import com.example.booking.exception.CustomException;
import com.example.booking.exception.booking.BookingDurationException;
import com.example.booking.exception.booking.CompanyIsNotAvailable;
import com.example.booking.exception.booking.CompanyIsNotWorking;
import com.example.booking.model.company.Company;
import com.example.booking.model.workinghour.WorkingHour;
import com.example.booking.service.booking.BookingReadService;
import com.example.booking.service.company.CompanyReadService;
import com.example.booking.service.workinghour.WorkingHourReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyAvailabilityService {

    private final BookingReadService bookingReadService;
    private final WorkingHourReadService workingHourReadService;
    private final CompanyReadService companyReadService;

    public Company getCompanyIfAvailable(Long companyId, LocalDateTime start, LocalDateTime end) {
        // Check: Duration
        if (!start.toLocalDate().equals(end.toLocalDate())) {
            throw new BookingDurationException();
        }

        // Get
        Company company = companyReadService.find(companyId);
        WorkingHour workingHour = workingHourReadService.find(company, start.getDayOfWeek());

        // Check: If company is working
        if (workingHour.getStart().isAfter(start.toLocalTime()) || workingHour.getEnd().isBefore(end.toLocalTime())) {
            throw new CompanyIsNotWorking(companyId, start);
        }

        // Check: If company is available
        if (bookingReadService.isReserved(companyId, start, end)) {
            throw new CompanyIsNotAvailable(companyId, start);
        }

        return company;
    }

    public Set<Company> findAllFreeCompanies() {
        LocalDateTime now = LocalDateTime.now();

        // Get: Working Companies
        List<WorkingHour> workingCompanies = workingHourReadService.findAllWorking(now.getDayOfWeek(), now.toLocalTime());
        if (workingCompanies.isEmpty()) {
            return Collections.emptySet();
        }

        return workingCompanies.stream()
                .filter(wh -> this.isCompanyAvailable(wh.getId().getCompany().getId(), now))
                .map(wh -> wh.getId().getCompany())
                .collect(Collectors.toSet());
    }

    public boolean isCompanyAvailable(Long companyId, LocalDateTime dateTime) {
        try {
            this.getCompanyIfAvailable(companyId, dateTime, dateTime);
        } catch (CustomException e) {
            return false;
        }
        return true;
    }
}
