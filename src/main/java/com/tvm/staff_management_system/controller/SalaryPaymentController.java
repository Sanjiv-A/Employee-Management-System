package com.tvm.staff_management_system.controller;

import com.tvm.staff_management_system.dto.SalaryPaymentDTO;
import com.tvm.staff_management_system.dto.StaffInfoDTO;
import com.tvm.staff_management_system.model.SalaryPayment;
import com.tvm.staff_management_system.service.AdvanceSalaryService;
import com.tvm.staff_management_system.service.AdvanceSalaryService;
import com.tvm.staff_management_system.service.SalaryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryPaymentController {

    @Autowired
    private SalaryPaymentService salaryPaymentService;
    @Autowired
    private AdvanceSalaryService advanceSalaryService;


    @PostMapping("/generate")
    public ResponseEntity<SalaryPaymentDTO> generateSalary(
            @RequestParam Long staffId,
            @RequestParam String month,
            @RequestParam Integer totalWorkingDays
    ) {
        // Call the service without passing baseSalary
        SalaryPayment salary = salaryPaymentService.generateSalary(staffId, month, totalWorkingDays);
        SalaryPaymentDTO dto = salaryPaymentService.toDTO(salary);
        return ResponseEntity.ok(dto);
    }



    @GetMapping("/staff/{staffId}/month/{month}")
    public ResponseEntity<SalaryPaymentDTO> getSalaryByStaffAndMonth(
            @PathVariable Long staffId,
            @PathVariable String month
    ) {
        SalaryPayment salary = salaryPaymentService.getSalaryByStaffAndMonth(staffId, month);
        SalaryPaymentDTO dto = salaryPaymentService.toDTO(salary);
        return ResponseEntity.ok(dto);
    }


//    @GetMapping("/staff/{staffId}/history")
//    public ResponseEntity<List<SalaryPaymentDTO>> getSalaryHistory(@PathVariable Long staffId) {
//        List<SalaryPaymentDTO> salaryList = salaryPaymentService.getSalaryHistory(staffId);
//        return ResponseEntity.ok(salaryList);
//    }
//    @GetMapping("/staff/{staffId}/advances/month/{month}")
//    public ResponseEntity<Double> getAdvanceDeduction(
//            @PathVariable Long staffId,
//            @PathVariable String month
//    ) {
//        double totalAdvance = advanceSalaryService.getAdvanceForStaffMonth(staffId, month);
//        return ResponseEntity.ok(totalAdvance);
//    }



//    @PutMapping("/{salaryId}")
//    public ResponseEntity<SalaryPaymentDTO> updateSalary(
//            @PathVariable Long salaryId,
//            @RequestBody SalaryPayment updatedSalary
//    ) {
//        SalaryPayment salary = salaryPaymentService.updateSalaryPayment(salaryId, updatedSalary);
//        SalaryPaymentDTO dto = salaryPaymentService.toDTO(salary);
//        return ResponseEntity.ok(dto);
//    }


    @DeleteMapping("/{salaryId}")
    public ResponseEntity<String> deleteSalary(@PathVariable Long salaryId) {
        salaryPaymentService.deleteSalaryById(salaryId);
        return ResponseEntity.ok("Salary record deleted successfully.");
    }
}
