package com.tvm.staff_management_system.dto;

import java.time.LocalDate;

public class AdvanceSalaryDTO {
    private Long advanceId;
    private LocalDate date;
    private Double amount;
    private StaffInfoDTO staff;

    public AdvanceSalaryDTO(Long advanceId, LocalDate date, Double amount, StaffInfoDTO staff) {
        this.advanceId = advanceId;
        this.date = date;
        this.amount = amount;
        this.staff = staff;
    }

    // Getters & Setters

    public Long getAdvanceId() { return advanceId; }
    public void setAdvanceId(Long advanceId) { this.advanceId = advanceId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public StaffInfoDTO getStaff() { return staff; }
    public void setStaff(StaffInfoDTO staff) { this.staff = staff; }
}
