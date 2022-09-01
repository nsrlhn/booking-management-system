package com.example.booking.request.slot;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SlotBookRequest {

    @NotNull
    @Schema(example = "Ensar ILHAN")
    private String customerName;
}
