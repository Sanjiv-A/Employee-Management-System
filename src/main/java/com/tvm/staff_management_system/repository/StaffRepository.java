package com.tvm.staff_management_system.repository;

import com.tvm.staff_management_system.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff ,Long> {
}
