package com.tvm.staff_management_system.controller;

import com.tvm.staff_management_system.dto.AdvanceSalaryDTO;
import com.tvm.staff_management_system.dto.StaffInfoDTO;
import com.tvm.staff_management_system.model.AdvanceSalary;
import com.tvm.staff_management_system.service.AdvanceSalaryService;
import com.tvm.staff_management_system.service.AdvanceSalaryService;
import com.tvm.staff_management_system.service.AdvanceSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advance")
public class AdvanceSalaryController {

    @Autowired
    private AdvanceSalaryService  advanceSalaryService;



    @PostMapping(consumes = "application/json", produces = "application/json")
    public AdvanceSalaryDTO saveAdvanceSalary(@RequestBody AdvanceSalaryDTO advanceSalaryDTO) {

        AdvanceSalary saved = advanceSalaryService.saveAdvanceSalary(advanceSalaryDTO);

        StaffInfoDTO staffDTO = new StaffInfoDTO(
                saved.getStaff().getId(),
                saved.getStaff().getName(),
                saved.getStaff().getRole(),
                saved.getStaff().getBaseSalary()
        );

        AdvanceSalaryDTO resultDTO = new AdvanceSalaryDTO(
                saved.getId(),
                saved.getDate(),
                saved.getAmount(),
                saved.getRemainingAmount(),
                staffDTO
        );

        return resultDTO;
    }


    @GetMapping
    public List<AdvanceSalaryDTO> getAllAdvanceSalary() {
        return advanceSalaryService.getAllAdvanceSalary();
    }


//
//
    @GetMapping("/{id}")
    public AdvanceSalaryDTO getAdvanceSalaryById(@PathVariable Long id){
        return advanceSalaryService.getAdvanceSalaryById(id);
    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdvanceSalary(@PathVariable Long id) {
        advanceSalaryService.deleteAdvanceSalaryById(id);
        return ResponseEntity.ok("AdvanceSalary deleted successfully");
    }
}
