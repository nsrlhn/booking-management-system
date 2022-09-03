package com.example.booking.request.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.DayOfWeek;

@Data
public class WorkingHourRequest implements Serializable {

    @NotNull
    private DayOfWeek day;

    @NotNull
    @Valid
    private Time start;

    @NotNull
    @Valid
    private Time end;

    @Data
    public static class Time implements Serializable {

        @NotNull
        @Max(23)
        @Min(0)
        @Schema(example = "8")
        private Integer hour;

        @NotNull
        @Max(59)
        @Min(0)
        @Schema(example = "00")
        private Integer minute;
    }
}
