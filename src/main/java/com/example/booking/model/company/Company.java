package com.example.booking.model.company;

import com.example.booking.enums.Status;
import com.example.booking.request.company.CompanyRequest;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static Company of(Long id) {
        Company company = new Company();
        company.setId(id);
        return company;
    }

    public <T extends CompanyRequest> void setFields(T request) {
        name = request.getName();
        phone = request.getPhone();
        longitude = request.getLongitude();
        latitude = request.getLatitude();
        status = request.getStatus();
    }
}
