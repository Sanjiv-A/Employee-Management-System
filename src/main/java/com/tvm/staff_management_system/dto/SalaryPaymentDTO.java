package com.tvm.staff_management_system.dto;

import java.time.LocalDate;

public class SalaryPaymentDTO {


        private Long salaryId;
        private String month;
        private Double baseSalary;
        private Integer totalWorkingDays;
        private Double presentDays;
        private Double earnedSalary;
        private Double totalAdvanceDeducted;
        private Double finalSalary;
        private LocalDate paidDate;
        private StaffInfoDTO staff;

    public SalaryPaymentDTO(Long salaryId, String month, Double baseSalary, Integer totalWorkingDays, Double presentDays, Double earnedSalary, Double totalAdvanceDeducted, Double finalSalary, LocalDate paidDate, StaffInfoDTO staff) {
        this.salaryId = salaryId;
        this.month = month;
        this.baseSalary = baseSalary;
        this.totalWorkingDays = totalWorkingDays;
        this.presentDays = presentDays;
        this.earnedSalary = earnedSalary;
        this.totalAdvanceDeducted = totalAdvanceDeducted;
        this.finalSalary = finalSalary;
        this.paidDate = paidDate;
        this.staff = staff;
    }


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

    public StaffInfoDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffInfoDTO staff) {
        this.staff = staff;
    }
}

