package com.tvm.staff_management_system.service;

import com.tvm.staff_management_system.dto.AdvanceSalaryDTO;
import com.tvm.staff_management_system.dto.StaffInfoDTO;
import com.tvm.staff_management_system.model.AdvanceSalary;
import com.tvm.staff_management_system.model.Staff;

import com.tvm.staff_management_system.repository.AdvanceSalaryRepository;
import com.tvm.staff_management_system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvanceSalaryService {

    @Autowired
    private AdvanceSalaryRepository advanceSalaryRepository;

    @Autowired
    private StaffRepository staffRepository;

    public AdvanceSalary saveAdvanceSalary(AdvanceSalary advanceSalary) {
        Long staffId = advanceSalary.getStaff().getId();
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + staffId));
        advanceSalary.setStaff(staff);
        return advanceSalaryRepository.save(advanceSalary);
    }

    public List<AdvanceSalaryDTO> getAllAdvanceSalary() {
        return advanceSalaryRepository.findAll().stream()
                .map(a -> new AdvanceSalaryDTO(
                        a.getAdvanceId(),
                        a.getDate(),
                        a.getAmount(),
                        new StaffInfoDTO(a.getStaff().getId(), a.getStaff().getName())
                )).collect(Collectors.toList());
    }

    public AdvanceSalaryDTO getAdvanceSalaryById(Long id) {
        AdvanceSalary a = advanceSalaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdvanceSalary not found with ID: " + id));
        return new AdvanceSalaryDTO(
                a.getAdvanceId(),
                a.getDate(),
                a.getAmount(),
                new StaffInfoDTO(a.getStaff().getId(), a.getStaff().getName())
        );
    }
    public AdvanceSalary updateAdvanceSalary(Long id, AdvanceSalary newAdvance) {
        return advanceSalaryRepository.findById(id)
                .map(existing -> {
                    existing.setDate(newAdvance.getDate());
                    existing.setAmount(newAdvance.getAmount());

                    return advanceSalaryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("AdvanceSalary not found with ID: " + id));
    }


    public void deleteAdvanceSalaryById(Long id) {
        if(advanceSalaryRepository.existsById(id)) {
            advanceSalaryRepository.deleteById(id);
        } else throw new RuntimeException("AdvanceSalary not found with ID: " + id);
    }


}
