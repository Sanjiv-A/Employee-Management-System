package com.tvm.staff_management_system.controller;

import com.tvm.staff_management_system.dto.AttendanceDTO;
import com.tvm.staff_management_system.dto.AttendanceSummaryDTO;
import com.tvm.staff_management_system.model.Attendance;
import com.tvm.staff_management_system.model.AttendanceStatus;
import com.tvm.staff_management_system.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/check-in")
    public ResponseEntity<Attendance> checkIn(
            @RequestParam Long staffId,
            @RequestParam AttendanceStatus status,
            @RequestParam(required = false) String remarks) {
        Attendance attendance = attendanceService.attendanceEntry(staffId, status, remarks);
        return ResponseEntity.ok(attendance);
    }
//
    @PutMapping("/check-out/{attendanceId}")
    public ResponseEntity<Attendance> checkOut(
            @PathVariable Long attendanceId,
            @RequestParam(required = false) String remarks) {
        Attendance attendance = attendanceService.attendanceCheckOut(attendanceId, remarks);
        return ResponseEntity.ok(attendance);
    }
    @GetMapping
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }


//
    @GetMapping("/{id}")
    public AttendanceDTO getAttendanceById(@PathVariable Long id){
        return attendanceService.getAttendanceById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendanceById(id);
        return ResponseEntity.ok("Attendance deleted successfully");
    }


        @GetMapping("/filter")
        public ResponseEntity<List<AttendanceDTO>> getFilteredAttendance(
                @RequestParam String filterType,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

            List<AttendanceDTO> filtered = attendanceService.getFilteredAttendance(filterType, date);
            return ResponseEntity.ok(filtered);
        }


        @GetMapping("/summary")
        public ResponseEntity<AttendanceSummaryDTO> getSummary(
                @RequestParam Long staffId,
                @RequestParam String startDate,
                @RequestParam String endDate) {

            LocalDate start = LocalDate.parse(startDate.trim());
            LocalDate end = LocalDate.parse(endDate.trim());

            AttendanceSummaryDTO summary = attendanceService.getAttendanceSummary(staffId, start, end);
            return ResponseEntity.ok(summary);
        }




}
