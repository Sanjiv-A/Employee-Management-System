package com.tvm.staff_management_system.repository;

import com.tvm.staff_management_system.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryPaymentRepository extends JpaRepository <SalaryPayment , Long>{
    List<SalaryPayment> findByStaffIdOrderByPaidDateDesc (Long staffId);
}
