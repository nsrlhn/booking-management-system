package com.example.booking.request.company;

import com.example.booking.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public abstract class CompanyRequest implements Serializable {

    @NotNull
    @Schema(example = "Company Test Name")
    private String name;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    @Schema(example = "5531312403")
    private String phone;

    @NotNull
    @Digits(integer = 9, fraction = 6)
    @Max(180)
    @Min(-180)
    @Schema(example = "28.781802")
    private BigDecimal longitude;

    @NotNull
    @Digits(integer = 8, fraction = 6)
    @Max(90)
    @Min(-90)
    @Schema(example = "40.990334")
    private BigDecimal latitude;

    @NotNull
    @Valid
    private List<WorkingHourRequest> workingHours;

    public abstract Status getStatus();
}
