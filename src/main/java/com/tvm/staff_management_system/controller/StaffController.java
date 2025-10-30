package com.tvm.staff_management_system.controller;

import com.tvm.staff_management_system.dto.StaffInfoDTO;
import com.tvm.staff_management_system.dto.StaffModelDTO;
import com.tvm.staff_management_system.model.Staff;
import com.tvm.staff_management_system.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Operation(summary = "Adding staffs " ,description ="Here we are adding the staffs in the data base "  )
    @PostMapping
    public ResponseEntity<Staff> saveStaff(@RequestBody StaffModelDTO staff ) {
        return ResponseEntity.ok(staffService.save(staff));
    }

    @GetMapping
    public List<StaffInfoDTO> getAllStaff() {
        return staffService.getAllStaff()
                .stream()
                .map(s -> new StaffInfoDTO(s.getId(), s.getName(), s.getRole(), s.getBaseSalary()))
                .toList();
    }

    @GetMapping("/{id}")
    public StaffInfoDTO getStaffById(@PathVariable Long id) {
        Staff s = staffService.getStaffById(id);
        return new StaffInfoDTO(s.getId(), s.getName(), s.getRole(), s.getBaseSalary());
    }

    @PutMapping(
            value = "/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<String> updateStaff(@PathVariable Long id, @RequestBody StaffModelDTO staff) {
        staffService.updateStaffById(id, staff);
        return ResponseEntity.ok("Staff with ID " + id + " updated successfully.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaffById(id);
        return ResponseEntity.ok("Staff deleted successfully.");
    }
}

