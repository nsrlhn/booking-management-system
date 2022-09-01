package com.example.booking.model.company;

import com.example.booking.enums.Status;
import com.example.booking.request.company.CompanyRequest;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    private String phone;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;

    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal latitude;

    @Column(nullable = false)
    private Status status;

    public <T extends CompanyRequest> void setFields(T request) {
        name = request.getName();
        phone = request.getPhone();
        longitude = request.getLongitude();
        latitude = request.getLatitude();
        status = request.getStatus();
    }
}
