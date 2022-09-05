package com.example.booking.repository.booking;

import com.example.booking.model.booking.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query(value = "SELECT EXISTS(SELECT * FROM booking AS b WHERE b.company_id = ?1 AND ((b.start <= ?2 AND ?2 < b.end) OR (b.start < ?3 AND ?3 <= b.end)))",
            nativeQuery = true)
    int numberOfOverlap(Long companyId, LocalDateTime start, LocalDateTime end);
}
