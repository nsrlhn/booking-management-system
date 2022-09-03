package com.example.booking.service.company;

import com.example.booking.model.company.Company;
import com.example.booking.model.company.WorkingHour;
import com.example.booking.model.company.WorkingHourId;
import com.example.booking.repository.company.WorkingHourRepository;
import com.example.booking.request.company.WorkingHourRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

// TODO : There may be a need of deleting working hour
@Service
@RequiredArgsConstructor
public class WorkingHourCrudService {

    private final WorkingHourRepository repository;

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
}
