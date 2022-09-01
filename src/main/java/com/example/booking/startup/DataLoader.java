package com.example.booking.startup;

import com.example.booking.enums.Status;
import com.example.booking.model.company.Company;
import com.example.booking.model.slot.Slot;
import com.example.booking.repository.company.CompanyRepository;
import com.example.booking.repository.slot.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final CompanyRepository companyRepository;
    private final SlotRepository slotRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void load() {
        Company company = new Company();
        company.setName("Test Company");
        company.setPhone("5531312403");
        company.setLongitude(new BigDecimal("28.979530"));
        company.setLatitude(new BigDecimal("41.015137"));
        company.setStatus(Status.Active);
        company = companyRepository.save(company);

        for (int i = 0; i < 24; i++) {
            Slot slot1 = new Slot();
            slot1.setCompany(company);
            slot1.setDate(LocalDate.now());
            slot1.setHour(LocalDateTime.now().getHour() + i);
            slotRepository.save(slot1);
        }

        for (int i = 0; i < 24; i++) {
            Slot slot1 = new Slot();
            slot1.setCompany(company);
            slot1.setDate(LocalDate.now().plusDays(1));
            slot1.setHour(LocalDateTime.now().getHour() + i);
            slotRepository.save(slot1);
        }
    }
}
