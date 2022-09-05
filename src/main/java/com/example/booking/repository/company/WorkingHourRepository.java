package com.example.booking.repository.company;

import com.example.booking.model.company.WorkingHour;
import com.example.booking.model.company.WorkingHourId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface WorkingHourRepository extends CrudRepository<WorkingHour, WorkingHourId> {

    @Query("SELECT wh FROM WorkingHour wh WHERE wh.id.dayOfWeek = ?1 and wh.start <= ?2 and wh.end > ?2")
    List<WorkingHour> findAllWorking(DayOfWeek day, LocalTime time);
}
