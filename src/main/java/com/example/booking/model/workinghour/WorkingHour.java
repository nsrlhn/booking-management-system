package com.example.booking.model.workinghour;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
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
