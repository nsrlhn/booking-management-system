package com.example.booking.startup;

import com.example.booking.enums.Status;
import com.example.booking.model.company.Company;
import com.example.booking.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final CompanyRepository companyRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void company() {
        Company company = new Company();
        company.setName("Test Company");
        company.setPhone("5531312403");
        company.setLongitude(new BigDecimal("28.979530"));
        company.setLatitude(new BigDecimal("41.015137"));
        company.setStatus(Status.Active);

        companyRepository.save(company);
    }
}
