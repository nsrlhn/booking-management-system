package com.example.booking.service.workinghour;

import com.example.booking.exception.workinghour.WorkingHourNotFoundException;
import com.example.booking.model.company.Company;
import com.example.booking.model.workinghour.WorkingHour;
import com.example.booking.model.workinghour.WorkingHourId;
import com.example.booking.repository.workinghour.WorkingHourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkingHourReadService {

    private final WorkingHourRepository repository;

    public WorkingHour find(Company company, DayOfWeek dayOfWeek) {
        return this.find(new WorkingHourId(company, dayOfWeek));
    }

    public WorkingHour find(WorkingHourId id) {
        return repository.findById(id)
                .orElseThrow(() -> new WorkingHourNotFoundException(id.getCompany().getId(), id.getDayOfWeek()));
    }

    public List<WorkingHour> findAllWorking(DayOfWeek dayOfWeek, LocalTime time) {
        return repository.findAllWorking(dayOfWeek, time);
    }
}
