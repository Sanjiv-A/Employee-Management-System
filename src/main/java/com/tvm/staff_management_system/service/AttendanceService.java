package com.tvm.staff_management_system.service;

import com.tvm.staff_management_system.dto.AdvanceSalaryDTO;
import com.tvm.staff_management_system.dto.AttendanceDTO;
import com.tvm.staff_management_system.dto.AttendanceSummaryDTO;
import com.tvm.staff_management_system.dto.StaffInfoDTO;
import com.tvm.staff_management_system.model.Attendance;
import com.tvm.staff_management_system.model.AttendanceStatus;
import com.tvm.staff_management_system.model.Staff;
import com.tvm.staff_management_system.repository.AdvanceSalaryRepository;
import com.tvm.staff_management_system.repository.AttendanceRepository;
import com.tvm.staff_management_system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private AdvanceSalaryService advanceSalaryService;
    @Autowired
    private AdvanceSalaryRepository advanceSalaryRepository;


    public Attendance attendanceEntry (Long staffId, AttendanceStatus status , String remarks){

        Staff staff = staffRepository.findById(staffId).orElseThrow(()-> new RuntimeException("Staff not found"));

        Attendance attendance = new Attendance();
        attendance.setStaff(staff);
        attendance.setStatus(status);
        attendance.setRemarks(remarks);
        attendance.setCheckInTime(LocalDateTime.now());
        if (status ==AttendanceStatus.LEAVE){
            attendance.setCheckOutTime(null);
        }
        return attendanceRepository.save(attendance);
    }



    public  Attendance attendanceCheckOut(Long attendanceId , String remarks){
        Attendance attendance= attendanceRepository.findById(attendanceId)
                .orElseThrow(()-> new RuntimeException("Attendance not found"));
        attendance.setCheckOutTime(LocalDateTime.now());
        return attendanceRepository.save(attendance);

    }

    public List<AttendanceDTO> getFilteredAttendance(String filterType, LocalDate date) {
        LocalDate startDate;
        LocalDate endDate = date;

        switch (filterType.toLowerCase()) {
            case "daily" -> startDate = endDate;
            case "weekly" -> startDate = endDate.minusDays(6); // last 7 days
            case "monthly" -> startDate = endDate.with(TemporalAdjusters.firstDayOfMonth());
            default -> throw new IllegalArgumentException("Invalid filter type. Use daily, weekly, or monthly");
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        return attendanceRepository.findByCheckInTimeBetween(startDateTime, endDateTime)
                .stream()
                .map(att -> {
                    Staff staff = att.getStaff(); // lazy load here
                    StaffInfoDTO staffDTO = new StaffInfoDTO(
                            staff.getId(),
                            staff.getName(),
                            staff.getRole(),
                            staff.getBaseSalary()
                    );

                    return new AttendanceDTO(
                            att.getAttendanceId(),
                            staffDTO,
                            att.getStatus().toString(),
                            att.getCheckInTime(),
                            att.getCheckOutTime(),
                            att.getRemarks()
                    );
                }).collect(Collectors.toList());
    }

    public List<AttendanceDTO> getAllAttendance() {
        List<Attendance> attendanceList = attendanceRepository.findAll();
        return attendanceList.stream().map(att -> {
            Staff staff = att.getStaff(); // lazy load here
            StaffInfoDTO staffDTO = new StaffInfoDTO(
                    staff.getId(),
                    staff.getName(),
                    staff.getRole(),
                    staff.getBaseSalary()
            );

            return new AttendanceDTO(
                    att.getAttendanceId(),
                    staffDTO,
                    att.getStatus().toString(),
                    att.getCheckInTime(),
                    att.getCheckOutTime(),
                    att.getRemarks()
            );
        }).collect(Collectors.toList());
    }




    public AttendanceDTO getAttendanceById(Long id) {
        Attendance a = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));

        Staff staff = a.getStaff(); // make sure this is loaded
        StaffInfoDTO staffDTO = new StaffInfoDTO(
                staff.getId(),     // or getId() depending on your Staff entity
                staff.getName(),
                staff.getRole(),
                staff.getBaseSalary()
        );

        return new AttendanceDTO(
                a.getAttendanceId(),
                staffDTO,
                a.getStatus().name(),
                a.getCheckInTime(),
                a.getCheckOutTime(),
                a.getRemarks()
        );
    }


    public AttendanceSummaryDTO getAttendanceSummary(Long staffId, LocalDate startDate, LocalDate endDate) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        List<Attendance> attendanceList = attendanceRepository.findByStaffAndCheckInTimeBetween(staff, startDateTime, endDateTime);


        long totalWorkedDays = attendanceList.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.PRESENT)
                .filter(a -> !(a.getCheckInTime().getDayOfWeek() == DayOfWeek.SATURDAY
                        || a.getCheckInTime().getDayOfWeek() == DayOfWeek.SUNDAY))
                .count();

        long totalLeaves = attendanceList.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.LEAVE)
                .count();

        StaffInfoDTO staffInfoDTO = new StaffInfoDTO(
                staff.getId(),
                staff.getName(),
                staff.getRole(),       // add role
                staff.getBaseSalary()  // add salary
        );

        return new AttendanceSummaryDTO(staffInfoDTO, totalWorkedDays, totalLeaves);
    }

//    public List<AdvanceSalaryDTO> getAdvancesByStaff(Long staffId) {
//        // Ensure staff exists
//        Staff staff = staffRepository.findById(staffId)
//                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + staffId));
//
//        // Fetch all advances for this staff
//        return advanceSalaryRepository.findByStaffIdOrderByDateAsc(staffId)
//                .stream()
//                .map(a -> {
//                    StaffInfoDTO staffDTO = new StaffInfoDTO(a.getStaff().getId(), a.getStaff().getName());
//                    return new AdvanceSalaryDTO(
//                            a.getAdvanceId(),
//                            a.getDate(),
//                            a.getAmount(),
//                            a.getRemainingAmount(), // include remainingAmount
//                            staffDTO
//                    );
//                })
//                .collect(Collectors.toList());
//    }



    public void deleteAttendanceById(Long id) {
        if(attendanceRepository.existsById(id)) {
            attendanceRepository.deleteById(id);
        } else throw new RuntimeException("Attendance not found with ID: " + id);
    }

}
