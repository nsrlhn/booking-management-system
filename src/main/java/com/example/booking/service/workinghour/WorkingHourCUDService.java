package com.example.booking.service.workinghour;

import com.example.booking.model.company.Company;
import com.example.booking.model.workinghour.WorkingHour;
import com.example.booking.model.workinghour.WorkingHourId;
import com.example.booking.repository.workinghour.WorkingHourRepository;
import com.example.booking.request.company.WorkingHourRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkingHourCUDService {

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
