package com.example.booking.request.company;

import com.example.booking.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyUpdateRequest extends CompanyRequest {

    @NotNull
    @Schema(example = "Active")
    private Status status;
}
