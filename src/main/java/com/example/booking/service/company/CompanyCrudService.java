package com.example.booking.service.company;

import com.example.booking.exception.company.CompanyNotFoundException;
import com.example.booking.model.company.Company;
import com.example.booking.repository.company.CompanyRepository;
import com.example.booking.request.company.CompanySaveRequest;
import com.example.booking.request.company.CompanyUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyCrudService {

    private final CompanyRepository repository;
    private final WorkingHourCrudService workingHourService;

    @Transactional
    public Company save(CompanySaveRequest request) {
        // Prepare
        Company company = new Company();
        company.setFields(request);

        // Save
        company = repository.save(company);

        // Save: Working Hours
        workingHourService.saveAll(company, request.getWorkingHours());

        return company;
    }

    public Company find(Long id) {
        return repository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Transactional
    public Company update(Long id, CompanyUpdateRequest request) {
        // Check & Get
        Company company = this.find(id);

        // Prepare
        company.setFields(request);

        // Update
        final Company result = repository.save(company);

        // Update: Working Hours
        workingHourService.updateOrSaveAll(company, request.getWorkingHours());

        return result;
    }
}
