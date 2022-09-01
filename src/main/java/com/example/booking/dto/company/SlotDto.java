package com.example.booking.dto.company;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class SlotDto implements Serializable {

    private final LocalDate date;

    private final int hour;

    public SlotDto(LocalDateTime localDateTime) {
        date = localDateTime.toLocalDate();
        hour = localDateTime.getHour();
    }
}
