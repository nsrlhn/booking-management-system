package com.example.booking.service.company;

import com.example.booking.model.company.Company;
import com.example.booking.repository.company.CompanyRepository;
import com.example.booking.request.company.CompanySaveRequest;
import com.example.booking.request.company.CompanyUpdateRequest;
import com.example.booking.service.workinghour.WorkingHourCUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyCUDService {

    private final CompanyRepository repository;
    private final CompanyReadService queryService;
    private final WorkingHourCUDService workingHourCUDService;

    @Transactional
    public Company save(CompanySaveRequest request) {
        // Prepare
        Company company = new Company();
        company.setFields(request);

        // Save
        company = repository.save(company);

        // Save: Working Hours
        workingHourCUDService.saveAll(company, request.getWorkingHours());

        return company;
    }

    @Transactional
    public Company update(Long id, CompanyUpdateRequest request) {
        // Check & Get
        Company company = queryService.find(id);

        // Prepare
        company.setFields(request);

        // Update
        final Company result = repository.save(company);

        // Update: Working Hours
        workingHourCUDService.updateOrSaveAll(company, request.getWorkingHours());

        return result;
    }
}
