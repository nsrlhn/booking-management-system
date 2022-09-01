package com.example.booking.model.slot;

import com.example.booking.model.company.Company;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "timeStampInHours"})})
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "company_id")
    private Company company;

    @Column(nullable = false)
    private Integer timeStampInHours;

    private String customerName;

    public static int convertToTimeStampInHours(LocalDate date, int hour) {
        return (int) (Timestamp.valueOf(LocalDateTime.of(date, LocalTime.of(hour, 0))).getTime() / 360000);
    }

    public static int now() {
        return (int) (System.currentTimeMillis() / 360000);
    }

    public boolean isBooked() {
        return customerName != null;
    }

    public boolean isPast() {
        return timeStampInHours < System.currentTimeMillis() / 360000;
    }

    public void setTimeStampInHours(LocalDate date, int hour) {
        timeStampInHours = convertToTimeStampInHours(date, hour);
    }

    public int compareDateAndTime(LocalDate date, Integer hour) {
        int other = convertToTimeStampInHours(date, hour);
        return Integer.compare(timeStampInHours, other);
    }
}
