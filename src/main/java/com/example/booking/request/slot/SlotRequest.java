package com.example.booking.request.slot;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public abstract class SlotRequest {

    @NotNull
    @Schema(example = "1")
    private Long companyId;

    @NotNull
    private LocalDate date;

    @NotNull
    @Min(0)
    @Max(23)
    private Integer hour;
}
