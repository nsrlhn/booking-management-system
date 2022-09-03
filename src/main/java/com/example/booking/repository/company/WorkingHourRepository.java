package com.example.booking.repository.company;

import com.example.booking.model.company.WorkingHour;
import com.example.booking.model.company.WorkingHourId;
import org.springframework.data.repository.CrudRepository;

public interface WorkingHourRepository extends CrudRepository<WorkingHour, WorkingHourId> {

}
