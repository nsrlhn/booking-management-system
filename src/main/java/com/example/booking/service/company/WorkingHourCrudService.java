package com.example.booking.service.company;

import com.example.booking.exception.company.CompanyNotFoundException;
import com.example.booking.exception.company.WorkingHourNotFoundException;
import com.example.booking.model.company.Company;
import com.example.booking.model.company.WorkingHour;
import com.example.booking.model.company.WorkingHourId;
import com.example.booking.repository.company.CompanyRepository;
import com.example.booking.repository.company.WorkingHourRepository;
import com.example.booking.request.company.WorkingHourRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkingHourCrudService {

    private final WorkingHourRepository repository;
    private final CompanyRepository companyRepository;

    public void saveAll(Company company, List<WorkingHourRequest> workingHours) {
        // TODO : Check:  if entity already exist
        // TODO : Check : start should be before end

        // Save
        repository.saveAll(workingHours.stream().map(wh -> this.prepare(company, wh)).collect(Collectors.toList()));
    }

    private WorkingHour prepare(Company company, WorkingHourRequest wh) {
        WorkingHour workingHour = new WorkingHour();
        workingHour.setId(new WorkingHourId(company, wh.getDay()));
        workingHour.setStart(LocalTime.of(wh.getStart().getHour(), wh.getStart().getMinute()));
        workingHour.setEnd(LocalTime.of(wh.getEnd().getHour(), wh.getEnd().getMinute()));
        return workingHour;
    }

    public void updateOrSaveAll(Company company, List<WorkingHourRequest> workingHours) {
        // TODO : Check : start should be before end

        // Save
        repository.saveAll(workingHours.stream().map(wh -> this.prepare(company, wh)).collect(Collectors.toList()));
    }

    public WorkingHour find(Long companyId, DayOfWeek dayOfWeek) {
        // Get: Company
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException(companyId));

        return repository.findById(new WorkingHourId(company, dayOfWeek))
                .orElseThrow(() -> new WorkingHourNotFoundException(companyId, dayOfWeek));
    }
}
