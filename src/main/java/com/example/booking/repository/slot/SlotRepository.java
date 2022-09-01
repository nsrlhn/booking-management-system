package com.example.booking.repository.slot;

import com.example.booking.model.slot.Slot;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface SlotRepository extends CrudRepository<Slot, Long> {

    boolean existsByCompanyIdAndDateAndHour(Long companyId, LocalDate date, Integer hour);

    List<Slot> findByDateAndHourAndCustomerNameIsNull(LocalDate date, Integer hour);
}
