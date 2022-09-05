package com.example.booking.service.company;

import com.example.booking.exception.CustomException;
import com.example.booking.model.company.Company;
import com.example.booking.model.company.WorkingHour;
import com.example.booking.repository.company.CompanyRepository;
import com.example.booking.repository.company.WorkingHourRepository;
import com.example.booking.service.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyQueryService {

    private final CompanyRepository companyRepository;
    private final WorkingHourRepository workingHourRepository;
    private final BookingService bookingService;

    public Set<Company> findAllFreeNow() {
        LocalDateTime now = LocalDateTime.now();

        // Get: Working Companies
        List<WorkingHour> workingCompanies = workingHourRepository.findAllWorking(now.getDayOfWeek(), now.toLocalTime());
        if (workingCompanies.isEmpty()) {
            return Collections.emptySet();
        }

        return workingCompanies.stream()
                .filter(wh -> this.isAvailable(wh.getId().getCompany().getId(), now))
                .map(wh -> wh.getId().getCompany())
                .collect(Collectors.toSet());
    }

    public boolean isAvailable(Long companyId, LocalDateTime dateTime) {
        try {
            bookingService.getCompanyIfAvailable(companyId, dateTime, dateTime);
        } catch (CustomException e) {
            return false;
        }
        return true;
    }

    public Company findNearest(BigDecimal latitude, BigDecimal longitude) {
        return companyRepository.findNearest(latitude, longitude);
    }
}
