package com.example.booking.exception.booking;

import com.example.booking.exception.CustomException;

import java.time.LocalDateTime;

public class CompanyIsNotAvailable extends CustomException {

    public CompanyIsNotAvailable(Long companyId, LocalDateTime time) {
        super(String.format("Company(id=%d) is not Available at %s", companyId, time));
    }
}
