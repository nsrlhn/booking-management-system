package com.example.booking.exception.company;

import com.example.booking.exception.CustomException;

import java.time.DayOfWeek;

public class WorkingHourNotFoundException extends CustomException {

    public WorkingHourNotFoundException(Long companyId, DayOfWeek dayOfWeek) {
        super(String.format("Company(id=%d) is Not Working at %s", companyId, dayOfWeek));
    }
}
