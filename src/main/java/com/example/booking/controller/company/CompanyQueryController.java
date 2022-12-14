package com.example.booking.controller.company;

import com.example.booking.model.company.Company;
import com.example.booking.service.availability.CompanyAvailabilityService;
import com.example.booking.service.company.CompanyReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"company"})
@Tag(name = "company-query")
@Validated
public class CompanyQueryController {

    private final CompanyAvailabilityService availabilityService;
    private final CompanyReadService companyReadService;

    @GetMapping(value = "allFree")
    @Operation(summary = "Find All Free Companies Now")
    public Set<Company> findAllFree() {
        return availabilityService.findAllFreeCompanies();
    }

    @GetMapping(value = "{id}/isAvailable")
    @Operation(summary = "Check If Company Available")
    public boolean checkIfCompanyAvailable(@PathVariable Long id,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Schema(example = "2022-09-01T17:30") LocalDateTime dateTime) {
        return availabilityService.isCompanyAvailable(id, dateTime);
    }

    @GetMapping(value = "nearest")
    @Operation(summary = "Find Nearest Companies")
    public Company nearest(@RequestParam @Digits(integer = 8, fraction = 6) @Max(90) @Min(-90) @Schema(example = "40.990334") BigDecimal latitude,
                           @RequestParam @Digits(integer = 9, fraction = 6) @Max(180) @Min(-180) @Schema(example = "28.781802") BigDecimal longitude) {
        return companyReadService.findNearest(latitude, longitude);
    }
}
