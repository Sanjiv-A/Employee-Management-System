package com.tvm.staff_management_system.repository;

import com.tvm.staff_management_system.model.AdvanceSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvanceSalaryRepository extends JpaRepository<AdvanceSalary, Long> {
    List<AdvanceSalary> findByStaffIdOrderByDateAsc (Long staffId);

}
