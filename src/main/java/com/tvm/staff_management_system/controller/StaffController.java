package com.tvm.staff_management_system.controller;

import com.tvm.staff_management_system.model.Staff;
import com.tvm.staff_management_system.repository.StaffRepository;
import com.tvm.staff_management_system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/staff")
public class StaffController {

@Autowired
private StaffService staffService;
    @PostMapping
    public ResponseEntity<Staff> saveStaff(@RequestBody Staff staff){
        Staff savestaff = staffService.save(staff);

        return ResponseEntity.ok(savestaff);
    }
    @GetMapping
    public List<Staff> getAllStaff(){
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id){
        return  staffService.getStaffById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        Staff updatedStaff = staffService.updateStaffById(id, staff);
        return ResponseEntity.ok(" Staff with ID " + id + " updated successfully.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id){
    staffService.deleteStaffById(id);
    return ResponseEntity.ok("Staff Deleted");

    }


}
