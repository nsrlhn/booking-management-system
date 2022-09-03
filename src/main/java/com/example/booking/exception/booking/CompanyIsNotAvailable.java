package com.example.booking.exception.booking;

import java.time.LocalDateTime;

public class CompanyIsNotAvailable extends RuntimeException {

    public CompanyIsNotAvailable(Long companyId, LocalDateTime time) {
        super(String.format("Company(id=%d) is not Available at %s", companyId, time));
    }
}
