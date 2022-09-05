package com.example.booking.exception.booking;

import com.example.booking.exception.CustomException;

import java.time.LocalDateTime;

public class CompanyIsNotWorking extends CustomException {

    public CompanyIsNotWorking(Long companyId, LocalDateTime time) {
        super(String.format("Company(id=%d) is not Working at %s", companyId, time));
    }
}
