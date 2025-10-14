package com.tvm.staff_management_system.controller;

import com.tvm.staff_management_system.dto.SalaryPaymentDTO;
import com.tvm.staff_management_system.model.SalaryPayment;
import com.tvm.staff_management_system.service.SalaryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryPaymentController {

    @Autowired
    private SalaryPaymentService salaryPaymentService;

    @PostMapping
    public ResponseEntity<SalaryPayment> saveSalaryPayment(@RequestBody SalaryPayment salaryPayment){
        return ResponseEntity.ok(salaryPaymentService.saveSalaryPayment(salaryPayment));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSalaryPayment(@PathVariable Long id, @RequestBody SalaryPayment salaryPayment){
        salaryPaymentService.updateSalaryPayment(id, salaryPayment);
        return ResponseEntity.ok("SalaryPayment with ID " + id + " updated successfully");
    }


    @GetMapping
    public List<SalaryPaymentDTO> getAllSalaryPayments() {
        return salaryPaymentService.getAllSalaryPayments();
    }

    @GetMapping("/{id}")
    public SalaryPaymentDTO getSalaryPaymentById(@PathVariable Long id){
        return salaryPaymentService.getSalaryPaymentById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalaryPayment(@PathVariable Long id) {
        salaryPaymentService.deleteSalaryPaymentById(id);
        return ResponseEntity.ok("SalaryPayment deleted successfully");
    }
}
