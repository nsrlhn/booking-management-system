package com.example.booking.repository.company;

import com.example.booking.model.company.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    /**
     * SQRT of the formula below gives the real distance in miles. See also "Haversine formula"
     */
    @Query(value = "SELECT *, " +
            "POW(69.1 * (latitude - ?1), 2) + POW(69.1 * (?2 - longitude) * COS(latitude / 57.3), 2) AS distance " +
            "FROM company c " +
            "ORDER BY distance " +
            "LIMIT 1",
            nativeQuery = true)
    Company findNearest(BigDecimal latitude, BigDecimal longitude);
}
