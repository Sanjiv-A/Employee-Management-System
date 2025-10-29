package com.tvm.staff_management_system.repository;

import com.tvm.staff_management_system.model.Attendance;
import com.tvm.staff_management_system.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Filter by staff and check-in time between two dates
    List<Attendance> findByStaffAndCheckInTimeBetween(Staff staff, LocalDateTime startDateTime, LocalDateTime endDateTime);

    // Or by check-in time only
    List<Attendance> findByCheckInTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Attendance> findByStaffIdAndAttendanceDateBetween(Long staffId, LocalDate start, LocalDate end);

}

