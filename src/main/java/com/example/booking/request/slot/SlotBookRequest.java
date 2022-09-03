package com.example.booking.request.slot;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SlotBookRequest implements Serializable {

    @NotNull
    @Schema(example = "Ensar ILHAN")
    private String customerName;
}
