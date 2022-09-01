package com.example.booking.service.company;

import com.example.booking.model.company.Company;
import com.example.booking.model.slot.Slot;
import com.example.booking.repository.slot.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanySearchService {

    private final SlotRepository slotRepository;

    public Set<Company> findAllFree() {
        return slotRepository.findByCustomerNameIsNullAndTimeStampInHours(Slot.now())
                .stream()
                .map(Slot::getCompany)
                .collect(Collectors.toSet());
    }
}
