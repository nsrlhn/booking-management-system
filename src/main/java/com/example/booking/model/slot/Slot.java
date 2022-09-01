package com.example.booking.model.slot;

import com.example.booking.model.company.Company;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "timeStampInHours"})})
public class Slot {

    private static final int MILLIS_TO_HOUR = 3600000;
    private static final int SECOND_TO_HOUR = 3600;

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
        return convertToTimeStampInHours(LocalDateTime.of(date, LocalTime.of(hour, 0)));
    }

    public static int now() {
        return (int) (System.currentTimeMillis() / MILLIS_TO_HOUR);
    }

    public static int convertToTimeStampInHours(LocalDateTime dateTime) {
        return (int) (Timestamp.valueOf(dateTime).getTime() / MILLIS_TO_HOUR);
    }

    public boolean isBooked() {
        return customerName != null;
    }

    public boolean isPast() {
        return timeStampInHours < System.currentTimeMillis() / MILLIS_TO_HOUR;
    }

    public void setTimeStampInHours(LocalDate date, int hour) {
        timeStampInHours = convertToTimeStampInHours(date, hour);
    }

    public int compareDateAndTime(LocalDate date, Integer hour) {
        int other = convertToTimeStampInHours(date, hour);
        return Integer.compare(timeStampInHours, other);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofEpochSecond(((long) timeStampInHours) * SECOND_TO_HOUR, 0, OffsetDateTime.now().getOffset());
    }
}
