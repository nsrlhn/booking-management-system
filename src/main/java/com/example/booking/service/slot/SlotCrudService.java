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
        int timeStampInHours = Slot.convertToTimeStampInHours(request.getDate(), request.getHour());

        Company company = companyService.find(request.getCompanyId());
        this.checkUniqueness(request, timeStampInHours);

        // Prepare
        Slot slot = new Slot();
        slot.setTimeStampInHours(timeStampInHours);
        slot.setCompany(company);

        // Save
        return repository.save(slot);
    }

    private void checkUniqueness(SlotRequest request, int timeStampInHours) {
        if (repository.existsByCompanyIdAndTimeStampInHours(request.getCompanyId(), timeStampInHours)) {
            throw new SlotIsAlreadyAvailableException(request.getCompanyId(), request.getDate(), request.getHour());
        }
    }

    public Slot find(Long id) {
        return repository.findById(id).orElseThrow(() -> new SlotNotFoundException(id));
    }

    public Slot update(Long id, SlotUpdateRequest request) {
        // Check & Get
        int timeStampInHours = Slot.convertToTimeStampInHours(request.getDate(), request.getHour());

        Slot slot = this.find(id);

        boolean isCompanyChanged = !request.getCompanyId().equals(slot.getCompany().getId());
        boolean isDateOrTimeChanged = !slot.getTimeStampInHours().equals(timeStampInHours);

        if (slot.isBooked()) {
            throw new SlotIsAlreadyBookedException(slot.getId());
        }
        if (isCompanyChanged || isDateOrTimeChanged) {
            this.checkUniqueness(request, timeStampInHours);
        }

        Company company = isCompanyChanged ? companyService.find(request.getCompanyId()) : slot.getCompany();

        // Prepare
        slot.setTimeStampInHours(timeStampInHours);
        slot.setCompany(company);

        // Save
        return repository.save(slot);
    }
}
