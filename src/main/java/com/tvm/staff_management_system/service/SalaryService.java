//package com.tvm.staff_management_system.service;
//
//import com.tvm.staff_management_system.dto.SalaryPaymentDTO;
//import com.tvm.staff_management_system.dto.StaffInfoDTO;
//import com.tvm.staff_management_system.model.SalaryPayment;
//import com.tvm.staff_management_system.model.Staff;
//import com.tvm.staff_management_system.repository.SalaryPaymentRepository;
//import com.tvm.staff_management_system.repository.StaffRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class SalaryPaymentService {
//
//    @Autowired
//    private SalaryPaymentRepository salaryPaymentRepository;
//
//    @Autowired
//    private StaffRepository staffRepository;
//
//    public SalaryPayment saveSalaryPayment(SalaryPayment salaryPayment) {
//        Long staffId = salaryPayment.getStaff().getId();
//        Staff staff = staffRepository.findById(staffId)
//                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + staffId));
//        salaryPayment.setStaff(staff);
//        return salaryPaymentRepository.save(salaryPayment);
//    }
//
//    public List<SalaryPaymentDTO> getAllSalaryPayments() {
//        return salaryPaymentRepository.findAll().stream()
//                .map(s -> new SalaryPaymentDTO(
//                        s.getSalaryId(),
//                        s.getMonth(),
//                        s.getTotalSalary(),
//                        s.getTotalAdvance(),
//                        s.getFinalSalary(),
//                        s.getPaidDate(),
//                        s.getPdfPath(),
//                        new StaffInfoDTO(s.getStaff().getId(), s.getStaff().getName())
//                )).collect(Collectors.toList());
//    }
//
//    public SalaryPaymentDTO getSalaryPaymentById(Long id) {
//        SalaryPayment s = salaryPaymentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("SalaryPayment not found with ID: " + id));
//        return new SalaryPaymentDTO(
//                s.getSalaryId(),
//                s.getMonth(),
//                s.getTotalSalary(),
//                s.getTotalAdvance(),
//                s.getFinalSalary(),
//                s.getPaidDate(),
//                s.getPdfPath(),
//                new StaffInfoDTO(s.getStaff().getId(), s.getStaff().getName())
//        );
//    }
//
//    public void deleteSalaryPaymentById(Long id) {
//        if(salaryPaymentRepository.existsById(id)) {
//            salaryPaymentRepository.deleteById(id);
//        } else throw new RuntimeException("SalaryPayment not found with ID: " + id);
//    }
//}
