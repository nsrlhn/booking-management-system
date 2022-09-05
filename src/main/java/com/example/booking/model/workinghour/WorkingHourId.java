package com.example.booking.model.workinghour;

import com.example.booking.model.company.Company;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.DayOfWeek;

@Data
@Embeddable
public class WorkingHourId implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Company company;

    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    public WorkingHourId(Company company, DayOfWeek dayOfWeek) {
        this.company = company;
        this.dayOfWeek = dayOfWeek;
    }

    public WorkingHourId() {
    }
}
