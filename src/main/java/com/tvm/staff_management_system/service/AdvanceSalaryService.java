package com.tvm.staff_management_system.service;

import com.tvm.staff_management_system.dto.AdvanceSalaryDTO;
import com.tvm.staff_management_system.dto.StaffInfoDTO;
import com.tvm.staff_management_system.model.AdvanceSalary;
import com.tvm.staff_management_system.model.Staff;
import com.tvm.staff_management_system.repository.AdvanceSalaryRepository;
import com.tvm.staff_management_system.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvanceSalaryService {

    private final AdvanceSalaryRepository advanceSalaryRepository;
    private final StaffRepository staffRepository;

    public AdvanceSalaryService(AdvanceSalaryRepository advanceSalaryRepository,
                                StaffRepository staffRepository) {
        this.advanceSalaryRepository = advanceSalaryRepository;
        this.staffRepository = staffRepository;
    }



    public AdvanceSalary saveAdvanceSalary(AdvanceSalaryDTO advanceSalaryDTO) {

        Long staffId = advanceSalaryDTO.getStaff().getStaffId();
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        AdvanceSalary data = new AdvanceSalary();
        data.setDate(advanceSalaryDTO.getDate());
        data.setAmount(advanceSalaryDTO.getAmount());
        data.setStaff(staff);

        // Fetch latest record and calculate cumulative remaining
        AdvanceSalary lastAdvance = advanceSalaryRepository.findLatestByStaffId(staffId);
        double remainingAmount;

        if (lastAdvance != null) {
            remainingAmount = lastAdvance.getRemainingAmount() - advanceSalaryDTO.getAmount();
        } else {
            remainingAmount = staff.getBaseSalary() - advanceSalaryDTO.getAmount();
        }

        data.setRemainingAmount(remainingAmount);

        return advanceSalaryRepository.save(data);
    }


    public List<AdvanceSalaryDTO> getAllAdvanceSalary() {
        return advanceSalaryRepository.findAll().stream().map(a -> {
            StaffInfoDTO staffDTO = null;
            if (a.getStaff() != null) {  // <-- check for null
                staffDTO = new StaffInfoDTO(
                        a.getStaff().getId(),
                        a.getStaff().getName(),
                        a.getStaff().getRole(),
                        a.getStaff().getBaseSalary()
                );
            }

            return new AdvanceSalaryDTO(
                    a.getId(),
                    a.getDate(),
                    a.getAmount(),
                    a.getRemainingAmount(),
                    staffDTO
            );
        }).collect(Collectors.toList());
    }

    public AdvanceSalaryDTO getAdvanceSalaryById(Long id) {
        AdvanceSalary saved = advanceSalaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advance Salary not found"));

        StaffInfoDTO staffDTO = null;
        if (saved.getStaff() != null) {
            staffDTO = new StaffInfoDTO(
                    saved.getStaff().getId(),
                    saved.getStaff().getName(),
                    saved.getStaff().getRole(),
                    saved.getStaff().getBaseSalary()
            );
        }

        return new AdvanceSalaryDTO(
                saved.getId(),
                saved.getDate(),
                saved.getAmount(),
                saved.getRemainingAmount(),
                staffDTO
        );

    }


    // Update Advance Salary
    public AdvanceSalaryDTO updateAdvanceSalary(Long id, AdvanceSalary newAdvance) {
        AdvanceSalary updated = advanceSalaryRepository.findById(id)
                .map(existing -> {
                    existing.setDate(newAdvance.getDate());
                    existing.setAmount(newAdvance.getAmount());
                    existing.setRemainingAmount(newAdvance.getRemainingAmount());
                    return advanceSalaryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("AdvanceSalary not found with ID: " + id));

        return new AdvanceSalaryDTO(
                updated.getId(),
                updated.getDate(),
                updated.getAmount(),
                updated.getRemainingAmount(),
                new StaffInfoDTO(updated.getStaff().getId(), updated.getStaff().getName())
        );
    }

    // Delete Advance Salary
    public void deleteAdvanceSalaryById(Long id) {
        if (advanceSalaryRepository.existsById(id)) {
            advanceSalaryRepository.deleteById(id);
        } else throw new RuntimeException("AdvanceSalary not found with ID: " + id);
    }
}
