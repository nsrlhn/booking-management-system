package com.example.booking.startup;

import com.example.booking.enums.Status;
import com.example.booking.model.company.Company;
import com.example.booking.model.company.WorkingHour;
import com.example.booking.model.company.WorkingHourId;
import com.example.booking.repository.company.CompanyRepository;
import com.example.booking.repository.company.WorkingHourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final CompanyRepository companyRepository;
    private final WorkingHourRepository workingHourRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void load() {
        Company company = new Company();
        company.setName("Company in Istanbul");
        company.setPhone("5531312403");
        company.setLatitude(new BigDecimal("41.015137"));
        company.setLongitude(new BigDecimal("28.979530"));
        company.setStatus(Status.Active);
        company = companyRepository.save(company);

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            WorkingHour wh = new WorkingHour();
            wh.setId(new WorkingHourId(company, dayOfWeek));
            wh.setStart(LocalTime.of(9, 0));
            wh.setEnd(LocalTime.of(18, 0));
            workingHourRepository.save(wh);
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
