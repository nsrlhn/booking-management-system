package com.example.booking.model.slot;

import com.example.booking.model.company.Company;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "date", "hour"})})
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "company_id")
    private Company company;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer hour;

    private String customerName;

    public boolean isBooked() {
        return customerName != null;
    }
}
