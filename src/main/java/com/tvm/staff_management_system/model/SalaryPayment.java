package com.tvm.staff_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SalaryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    private String month;                // e.g., "2025-10"
    private Double baseSalary;        // Full salary
    private Integer totalWorkingDays;    // Total working days in month (excluding weekends)
    private Double presentDays;          // Days employee was present
    private Double earnedSalary;         // baseSalary * (presentDays / totalWorkingDays)
    private Double totalAdvanceDeducted;
    private Double finalSalary;          // earnedSalary - totalAdvanceDeducted
    private LocalDate paidDate;





    @ManyToOne
    @JoinColumn(name = "staff_id")
    @JsonBackReference
    private Staff staff;

    // Getters & Setters


    public Long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Long salaryId) {
        this.salaryId = salaryId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Integer totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public Double getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(Double presentDays) {
        this.presentDays = presentDays;
    }

    public Double getEarnedSalary() {
        return earnedSalary;
    }

    public void setEarnedSalary(Double earnedSalary) {
        this.earnedSalary = earnedSalary;
    }

    public Double getTotalAdvanceDeducted() {
        return totalAdvanceDeducted;
    }

    public void setTotalAdvanceDeducted(Double totalAdvanceDeducted) {
        this.totalAdvanceDeducted = totalAdvanceDeducted;
    }

    public Double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(Double finalSalary) {
        this.finalSalary = finalSalary;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }



    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }



    @Override
    public String toString() {
        return "SalaryPayment{" +
                "staff=" + (staff != null ? staff.getName() : "N/A") +
                ", month='" + month + '\'' +
                ", finalSalary=" + finalSalary +
                '}';
    }

}
