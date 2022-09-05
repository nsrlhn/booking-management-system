package com.example.booking.controller.company;

import com.example.booking.model.company.Company;
import com.example.booking.request.company.CompanySaveRequest;
import com.example.booking.request.company.CompanyUpdateRequest;
import com.example.booking.service.company.CompanyCUDService;
import com.example.booking.service.company.CompanyReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"company"})
@Tag(name = "company-crud")
public class CompanyCrudController {

    private final CompanyCUDService service;
    private final CompanyReadService findService;

    @GetMapping(value = "{id}")
    @Operation(summary = "Find Company by Id", description = "Throw error if company is not found.")
    public Company find(@PathVariable Long id) {
        return findService.find(id);
    }

    @PostMapping
    @Operation(summary = "Save Company")
    public Company save(@Valid @RequestBody CompanySaveRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Company", description = "Throw error if company is not found.")
    public Company update(@PathVariable Long id, @Valid @RequestBody CompanyUpdateRequest request) {
        return service.update(id, request);
    }
}
