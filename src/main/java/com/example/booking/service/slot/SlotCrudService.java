package com.example.booking.service.slot;

import com.example.booking.exception.slot.SlotIsAlreadyAvailableException;
import com.example.booking.exception.slot.SlotIsAlreadyBookedException;
import com.example.booking.exception.slot.SlotNotFoundException;
import com.example.booking.model.company.Company;
import com.example.booking.model.slot.Slot;
import com.example.booking.repository.slot.SlotRepository;
import com.example.booking.request.slot.SlotRequest;
import com.example.booking.request.slot.SlotSaveRequest;
import com.example.booking.request.slot.SlotUpdateRequest;
import com.example.booking.service.company.CompanyCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlotCrudService {

    private final SlotRepository repository;
    private final CompanyCrudService companyService;

    public Slot save(SlotSaveRequest request) {
        // Check & Get
        Company company = companyService.find(request.getCompanyId());
        this.checkUniqueness(request);

        // Prepare
        Slot slot = new Slot();
        slot.setDate(request.getDate());
        slot.setHour(request.getHour());
        slot.setCompany(company);

        // Save
        return repository.save(slot);
    }

    private void checkUniqueness(SlotRequest request) {
        if (repository.existsByCompanyIdAndDateAndHour(request.getCompanyId(), request.getDate(), request.getHour())) {
            throw new SlotIsAlreadyAvailableException(request.getCompanyId(), request.getDate(), request.getHour());
        }
    }

    public Slot find(Long id) {
        return repository.findById(id).orElseThrow(() -> new SlotNotFoundException(id));
    }

    public Slot update(Long id, SlotUpdateRequest request) {
        // Check & Get
        Slot slot = this.find(id);

        boolean isCompanyChanged = !request.getCompanyId().equals(slot.getCompany().getId());
        boolean isHourChanged = !request.getHour().equals(slot.getHour());
        boolean isDateChanged = !request.getDate().equals(slot.getDate());

        if (slot.isBooked()) {
            throw new SlotIsAlreadyBookedException(slot.getId());
        }
        if (isCompanyChanged || isDateChanged || isHourChanged) {
            this.checkUniqueness(request);
        }

        Company company = isCompanyChanged ? companyService.find(request.getCompanyId()) : slot.getCompany();

        // Prepare
        slot.setDate(request.getDate());
        slot.setHour(request.getHour());
        slot.setCompany(company);

        // Save
        return repository.save(slot);
    }
}
