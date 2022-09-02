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

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final CompanyRepository companyRepository;
    private final SlotRepository slotRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void load() {
        Company company = new Company();
        company.setName("Company in Istanbul");
        company.setPhone("5531312403");
        company.setLatitude(new BigDecimal("41.015137"));
        company.setLongitude(new BigDecimal("28.979530"));
        company.setStatus(Status.Active);
        company = companyRepository.save(company);

        int currentHour = Slot.now();
        for (int i = 0; i < 48; i++) {
            Slot slot = new Slot();
            slot.setCompany(company);
            slot.setTimeStampInHours(currentHour + i);
            slotRepository.save(slot);
        }

        Company company2 = new Company();
        company2.setName("Company in Ankara");
        company2.setPhone("5531312403");
        company2.setLatitude(new BigDecimal("39.925533"));
        company2.setLongitude(new BigDecimal("32.866287"));
        company2.setStatus(Status.Active);
        companyRepository.save(company2);
    }
}
