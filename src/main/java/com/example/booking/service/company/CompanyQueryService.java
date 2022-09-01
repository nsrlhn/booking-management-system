package com.example.booking.service.company;

import com.example.booking.exception.company.CompanyNotFoundException;
import com.example.booking.model.company.Company;
import com.example.booking.model.slot.Slot;
import com.example.booking.repository.company.CompanyRepository;
import com.example.booking.repository.slot.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyQueryService {

    private final SlotRepository slotRepository;
    private final CompanyRepository repository;

    public Set<Company> findAllFree() {
        return slotRepository.findByCustomerNameIsNullAndTimeStampInHours(Slot.now())
                .stream()
                .map(Slot::getCompany)
                .collect(Collectors.toSet());
    }

    public boolean isCompanyAvailable(Long companyId, LocalDateTime dateTime) {
        // Check
        if (!repository.existsById(companyId)) {
            throw new CompanyNotFoundException(companyId);
        }

        return slotRepository.existsByCustomerNameIsNullAndCompanyIdAndTimeStampInHours(companyId, Slot.convertToTimeStampInHours(dateTime));
    }

    public List<Slot> getSlots(Long companyId, Integer withinDays) {
        int startHour = Slot.now();
        int endHour = Slot.now() + withinDays * 24;
        return slotRepository.findByCustomerNameIsNullAndCompanyIdAndTimeStampInHoursBetween(companyId, startHour, endHour);
    }
}
