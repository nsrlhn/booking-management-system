package com.example.booking.request.company;

import com.example.booking.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanySaveRequest extends CompanyRequest {

    @Schema(example = "Active")
    private Status status = Status.Active;
}
