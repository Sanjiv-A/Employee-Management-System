package com.tvm.staff_management_system.dto;

import java.time.LocalDate;

public class SalaryPaymentDTO {

    private Long salaryId;
    private String month;
    private Double totalSalary;
    private Double totalAdvance;
    private Double finalSalary;
    private LocalDate paidDate;
    private String pdfPath;
    private StaffInfoDTO staff;

    public SalaryPaymentDTO(Long salaryId, String month, Double totalSalary, Double totalAdvance,
                            Double finalSalary, LocalDate paidDate, String pdfPath, StaffInfoDTO staff) {
        this.salaryId = salaryId;
        this.month = month;
        this.totalSalary = totalSalary;
        this.totalAdvance = totalAdvance;
        this.finalSalary = finalSalary;
        this.paidDate = paidDate;
        this.pdfPath = pdfPath;
        this.staff = staff;
    }

    // Getters and Setters

    public Long getSalaryId() { return salaryId; }
    public void setSalaryId(Long salaryId) { this.salaryId = salaryId; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public Double getTotalSalary() { return totalSalary; }
    public void setTotalSalary(Double totalSalary) { this.totalSalary = totalSalary; }

    public Double getTotalAdvance() { return totalAdvance; }
    public void setTotalAdvance(Double totalAdvance) { this.totalAdvance = totalAdvance; }

    public Double getFinalSalary() { return finalSalary; }
    public void setFinalSalary(Double finalSalary) { this.finalSalary = finalSalary; }

    public LocalDate getPaidDate() { return paidDate; }
    public void setPaidDate(LocalDate paidDate) { this.paidDate = paidDate; }

    public String getPdfPath() { return pdfPath; }
    public void setPdfPath(String pdfPath) { this.pdfPath = pdfPath; }

    public StaffInfoDTO getStaff() { return staff; }
    public void setStaff(StaffInfoDTO staff) { this.staff = staff; }
}

