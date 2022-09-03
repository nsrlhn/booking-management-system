package com.example.booking.model.company;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "company_working_hours")
public class WorkingHour implements Serializable {

    @EmbeddedId
    private WorkingHourId id = new WorkingHourId();

    @Column(nullable = false)
    private LocalTime start;

    @Column(nullable = false)
    private LocalTime end;
}
