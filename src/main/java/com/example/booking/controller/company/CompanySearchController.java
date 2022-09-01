package com.example.booking.controller.company;

import com.example.booking.model.company.Company;
import com.example.booking.service.company.CompanySearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"company"})
@Tag(name = "company-search")
public class CompanySearchController {

    private final CompanySearchService service;

    @GetMapping(value = "allFree")
    @Operation(summary = "Find All Free Companies Now")
    public Set<Company> findAllFree() {
        return service.findAllFree();
    }
}
