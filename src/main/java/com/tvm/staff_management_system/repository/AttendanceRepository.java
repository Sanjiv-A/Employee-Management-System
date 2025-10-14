package com.tvm.staff_management_system.repository;

import com.tvm.staff_management_system.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStaffId(Long staffId);
}
