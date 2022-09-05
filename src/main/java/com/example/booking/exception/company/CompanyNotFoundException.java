package com.example.booking.exception.company;

import com.example.booking.exception.CustomException;

public class CompanyNotFoundException extends CustomException {

    public CompanyNotFoundException(Long id) {
        super(String.format("Company(id=%d) Not Found", id));
    }
}
