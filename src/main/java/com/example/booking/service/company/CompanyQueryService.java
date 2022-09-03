package com.example.booking.service.company;

import com.example.booking.exception.company.CompanyNotFoundException;
import com.example.booking.model.company.Company;
import com.example.booking.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompanyQueryService {

    private final CompanyRepository companyRepository;

    public Set<Company> findAllFree() {
        // TODO
        return null;
    }

    public boolean isCompanyAvailable(Long companyId, LocalDateTime dateTime) {
        // Check
        if (!companyRepository.existsById(companyId)) {
            throw new CompanyNotFoundException(companyId);
        }

        // TODO
        return false;
    }

    public Company findNearest(BigDecimal latitude, BigDecimal longitude) {
        return companyRepository.findNearest(latitude, longitude);
    }

    public List<Company> getAvailability(Long id, Integer withinDays) {
        // TODO
        return null;
    }
}
