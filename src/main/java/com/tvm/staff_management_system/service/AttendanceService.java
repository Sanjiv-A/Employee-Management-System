package com.tvm.staff_management_system.service;

import com.tvm.staff_management_system.dto.AttendanceDTO;
import com.tvm.staff_management_system.dto.StaffInfoDTO;
import com.tvm.staff_management_system.model.Attendance;
import com.tvm.staff_management_system.model.Staff;
import com.tvm.staff_management_system.repository.AttendanceRepository;
import com.tvm.staff_management_system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StaffRepository staffRepository;

    public Attendance saveAttendance(Attendance attendance) {

        Long staffId = attendance.getStaff().getId();
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + staffId));
        attendance.setStaff(staff);
        return attendanceRepository.save(attendance);
    }
    public Attendance updateAttendance(Long id, Attendance newAttendance) {
        return attendanceRepository.findById(id).map(existing -> {
            existing.setDate(newAttendance.getDate());
            existing.setStatus(newAttendance.getStatus());
            existing.setRemarks(newAttendance.getRemarks());

            return attendanceRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));
    }


    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepository.findAll().stream()
                .map(a -> new AttendanceDTO(
                        a.getAttendanceId(),
                        a.getDate(),
                        a.getStatus().name(),
                        a.getRemarks(),
                        new StaffInfoDTO(a.getStaff().getId(), a.getStaff().getName())
                )).collect(Collectors.toList());
    }

    public AttendanceDTO getAttendanceById(Long id) {
        Attendance a = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));
        return new AttendanceDTO(
                a.getAttendanceId(),
                a.getDate(),
                a.getStatus().name(),
                a.getRemarks(),
                new StaffInfoDTO(a.getStaff().getId(), a.getStaff().getName())
        );
    }

    public void deleteAttendanceById(Long id) {
        if(attendanceRepository.existsById(id)) {
            attendanceRepository.deleteById(id);
        } else throw new RuntimeException("Attendance not found with ID: " + id);
    }
}
