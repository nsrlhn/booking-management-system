package com.example.booking.exception.company;

import javax.persistence.EntityNotFoundException;

public class CompanyNotFoundException extends EntityNotFoundException {

    public CompanyNotFoundException(Long id) {
        super(String.format("Company(id=%d) Not Found", id));
    }
}
