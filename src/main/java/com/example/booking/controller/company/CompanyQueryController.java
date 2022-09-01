package com.example.booking.controller.company;

import com.example.booking.model.company.Company;
import com.example.booking.service.company.CompanyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"company"})
@Tag(name = "company-query")
public class CompanyQueryController {

    private final CompanyQueryService service;

    @GetMapping(value = "allFree")
    @Operation(summary = "Find All Free Companies Now")
    public Set<Company> findAllFree() {
        return service.findAllFree();
    }

    @GetMapping(value = "isAvailable")
    @Operation(summary = "Find All Free Companies Now")
    public boolean checkIfCompanyAvailable(@Schema(example = "1") @RequestParam Long companyId,
                                           @Schema(example = "2022-09-01T17:30") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return service.isCompanyAvailable(companyId, dateTime);
    }
}
