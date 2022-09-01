package com.example.booking.repository.slot;

import com.example.booking.model.slot.Slot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SlotRepository extends CrudRepository<Slot, Long> {

    boolean existsByCompanyIdAndTimeStampInHours(Long companyId, Integer timeStampInHours);

    List<Slot> findByCustomerNameIsNullAndTimeStampInHours(Integer timeStampInHours);

    boolean existsByCustomerNameIsNullAndCompanyIdAndTimeStampInHours(Long companyId, Integer timeStampInHours);

    List<Slot> findByCustomerNameIsNullAndCompanyIdAndTimeStampInHoursBetween(Long companyId, int fromHour, int toHour);
}
