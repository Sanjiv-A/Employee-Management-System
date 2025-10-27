package com.tvm.staff_management_system.service;

import com.tvm.staff_management_system.dto.SalaryPaymentDTO;
import com.tvm.staff_management_system.dto.StaffInfoDTO;
import com.tvm.staff_management_system.model.Attendance;
import com.tvm.staff_management_system.model.AttendanceStatus;
import com.tvm.staff_management_system.model.SalaryPayment;
import com.tvm.staff_management_system.model.Staff;
import com.tvm.staff_management_system.repository.AdvanceSalaryRepository;
import com.tvm.staff_management_system.repository.AttendanceRepository;
import com.tvm.staff_management_system.repository.SalaryPaymentRepository;
import com.tvm.staff_management_system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryPaymentService {

    @Autowired
    private SalaryPaymentRepository salaryPaymentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private AdvanceSalaryRepository advanceSalaryRepository;

    // Convert entity to DTO
    public SalaryPaymentDTO toDTO(SalaryPayment s) {
        return new SalaryPaymentDTO(
                s.getSalaryId(),
                s.getMonth(),
                s.getBaseSalary(),
                s.getTotalWorkingDays(),
                s.getPresentDays(),
                s.getEarnedSalary(),
                s.getTotalAdvanceDeducted(),
                s.getFinalSalary(),
                s.getPaidDate(),
                new StaffInfoDTO(
                        s.getStaff().getId(),
                        s.getStaff().getName(),
                        s.getStaff().getRole(),
                        s.getStaff().getBaseSalary()
                )
        );
    }

    public SalaryPayment generateSalary(Long staffId, String month, Integer totalWorkingDays) {
        // Fetch staff
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + staffId));

        LocalDate firstDay = LocalDate.parse(month + "-01");
        LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());

        // Fetch attendance for the month
        List<Attendance> attendanceList = attendanceRepository.findByStaffIdAndAttendanceDateBetween(staffId, firstDay, lastDay);

        // Count present days (excluding weekends)
        long presentDaysCount = attendanceList.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.PRESENT)
                .filter(a -> !(a.getCheckInTime().getDayOfWeek() == DayOfWeek.SATURDAY
                        || a.getCheckInTime().getDayOfWeek() == DayOfWeek.SUNDAY))
                .count();
        double presentDays = (double) presentDaysCount;

        // Calculate total advance deducted for this month
        double totalAdvanceDeducted = advanceSalaryRepository.findByStaffIdOrderByDateAsc(staffId).stream()
                .filter(a -> a.getDate().getMonthValue() == firstDay.getMonthValue() &&
                        a.getDate().getYear() == firstDay.getYear())
                .mapToDouble(a -> a.getAmount()) // deduct actual advance amount
                .sum();

        // Calculate earned salary based on staff's base salary
        double earnedSalary = (staff.getBaseSalary() / totalWorkingDays) * presentDays;
        double finalSalary = earnedSalary - totalAdvanceDeducted;

        // Create salary record
        SalaryPayment salary = new SalaryPayment();
        salary.setStaff(staff);
        salary.setMonth(month);
        salary.setBaseSalary(staff.getBaseSalary());    // use staff's base salary
        salary.setTotalWorkingDays(totalWorkingDays);
        salary.setPresentDays(presentDays);             // correctly set present days
        salary.setEarnedSalary(earnedSalary);
        salary.setTotalAdvanceDeducted(totalAdvanceDeducted);
        salary.setFinalSalary(finalSalary);
        salary.setPaidDate(lastDay);                    // Salary paid at the end of month

        return salaryPaymentRepository.save(salary);
    }


    // Get salary by staff & month
    public SalaryPayment getSalaryByStaffAndMonth(Long staffId, String month) {
        return salaryPaymentRepository.findByStaffIdOrderByPaidDateDesc(staffId).stream()
                .filter(s -> s.getMonth().equals(month))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Salary not found for staff " + staffId + " in month " + month));
    }

    // Get salary history of a staff
    public List<SalaryPaymentDTO> getSalaryHistory(Long staffId) {
        return salaryPaymentRepository.findByStaffIdOrderByPaidDateDesc(staffId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }



//    // Update salary manually
//    public SalaryPayment updateSalaryPayment(Long id, SalaryPayment newSalary) {
//        return salaryPaymentRepository.findById(id)
//                .map(existing -> {
//                    existing.setMonth(newSalary.getMonth());
//                    existing.setBaseSalary(newSalary.getBaseSalary());
//                    existing.setTotalAdvanceDeducted(newSalary.getTotalAdvanceDeducted());
//                    existing.setFinalSalary(newSalary.getFinalSalary());
//                    existing.setEarnedSalary(newSalary.getEarnedSalary());
//                    existing.setPaidDate(newSalary.getPaidDate());
//
//                    return salaryPaymentRepository.save(existing);
//                })
//                .orElseThrow(() -> new RuntimeException("SalaryPayment not found with ID: " + id));
//    }

    //Delete salary record
    public void deleteSalaryById(Long id) {
        if (salaryPaymentRepository.existsById(id)) {
            salaryPaymentRepository.deleteById(id);
        } else throw new RuntimeException("SalaryPayment not found with ID: " + id);
    }

}
