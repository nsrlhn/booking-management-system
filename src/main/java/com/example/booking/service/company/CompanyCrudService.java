package com.example.booking.service.company;

import com.example.booking.exception.company.CompanyNotFoundException;
import com.example.booking.model.company.Company;
import com.example.booking.repository.company.CompanyRepository;
import com.example.booking.request.company.CompanySaveRequest;
import com.example.booking.request.company.CompanyUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyCrudService {

    private final CompanyRepository repository;

    public Company save(CompanySaveRequest request) {
        // Prepare
        Company company = new Company();
        company.setFields(request);

        // Save
        return repository.save(company);
    }

    public Company find(Long id) {
        return repository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public Company update(Long id, CompanyUpdateRequest request) {
        // Check & Get
        Company company = this.find(id);

        // Prepare
        company.setFields(request);

        // Save
        return repository.save(company);
    }
}
