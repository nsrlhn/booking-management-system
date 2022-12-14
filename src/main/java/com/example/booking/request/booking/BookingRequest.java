package com.example.booking.request.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BookingRequest implements Serializable {

    @NotNull
    @Schema(example = "1")
    private Long companyId;

    @NotNull
    private LocalDate date;

    @NotNull
    @Min(0)
    @Max(23)
    @Schema(example = "14")
    private Integer hour;

    @NotNull
    @Schema(example = "Ensar ILHAN")
    private String customerName;
}
