package com.example.booking.exception.booking;

import java.time.LocalDateTime;

public class CompanyIsNotWorkingAtThatTime extends RuntimeException {

    public CompanyIsNotWorkingAtThatTime(Long companyId, LocalDateTime time) {
        super(String.format("Company(id=%d) is not Working at %s", companyId, time));
    }
}
