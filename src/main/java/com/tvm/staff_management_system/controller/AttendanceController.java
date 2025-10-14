package com.tvm.staff_management_system.controller;

import com.tvm.staff_management_system.dto.AttendanceDTO;
import com.tvm.staff_management_system.model.Attendance;
import com.tvm.staff_management_system.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Attendance> saveAttendance(@RequestBody Attendance attendance){
        return ResponseEntity.ok(attendanceService.saveAttendance(attendance));
    }

    @GetMapping
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance){
        attendanceService.updateAttendance(id, attendance);
        return ResponseEntity.ok("Attendance with ID " + id + " updated successfully");
    }


    @GetMapping("/{id}")
    public AttendanceDTO getAttendanceById(@PathVariable Long id){
        return attendanceService.getAttendanceById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendanceById(id);
        return ResponseEntity.ok("Attendance deleted successfully");
    }
}
