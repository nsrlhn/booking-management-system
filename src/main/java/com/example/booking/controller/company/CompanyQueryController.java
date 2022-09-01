package com.example.booking.controller.company;

import com.example.booking.dto.company.SlotDto;
import com.example.booking.model.company.Company;
import com.example.booking.service.company.CompanyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping(value = "{id}/isAvailable")
    @Operation(summary = "Check If Company Available")
    public boolean checkIfCompanyAvailable(@PathVariable Long id,
                                           @Schema(example = "2022-09-01T17:30") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return service.isCompanyAvailable(id, dateTime);
    }

    @GetMapping(value = "{id}/getSlots")
    @Operation(summary = "Check Time Available by Company for Next x Days")
    public List<SlotDto> getSlots(@PathVariable Long id, @RequestParam Integer withinDays) {
        return service.getSlots(id, withinDays)
                .stream()
                .map(s -> new SlotDto(s.getLocalDateTime()))
                .collect(Collectors.toList());
    }

}
