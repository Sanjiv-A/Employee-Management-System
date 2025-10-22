package com.tvm.staff_management_system.repository;

import com.tvm.staff_management_system.model.AdvanceSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvanceSalaryRepository extends JpaRepository<AdvanceSalary, Long> {
    List<AdvanceSalary> findByStaffIdOrderByDateAsc (Long staffId);
    @Query("SELECT a FROM AdvanceSalary a WHERE a.staff.id = :staffId ORDER BY a.date DESC LIMIT 1" )
    AdvanceSalary findLatestByStaffId(@Param("staffId") Long staffId);


}
