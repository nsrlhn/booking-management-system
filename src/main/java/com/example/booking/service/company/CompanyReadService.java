package com.example.booking.service.company;

import com.example.booking.exception.company.CompanyNotFoundException;
import com.example.booking.model.company.Company;
import com.example.booking.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CompanyReadService {

    private final CompanyRepository repository;

    public Company find(Long id) {
        return repository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public Company findNearest(BigDecimal latitude, BigDecimal longitude) {
        return repository.findNearest(latitude, longitude);
    }
}
