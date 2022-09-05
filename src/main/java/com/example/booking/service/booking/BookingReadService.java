package com.example.booking.service.booking;

import com.example.booking.repository.booking.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingReadService {

    private final BookingRepository repository;

    public boolean isReserved(Long companyId, LocalDateTime start, LocalDateTime end) {
        return repository.numberOfOverlap(companyId, start, end) != 0;
    }
}
