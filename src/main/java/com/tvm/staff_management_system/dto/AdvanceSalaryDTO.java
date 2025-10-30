package com.tvm.staff_management_system.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
public class AdvanceSalaryDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private Double remainingAmount;
    private StaffInfoDTO staff;


    public AdvanceSalaryDTO(Long id, LocalDate date, Double amount, Double remainingAmount, StaffInfoDTO staff) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.remainingAmount = remainingAmount;
        this.staff = staff;
    }


    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Double getRemainingAmount() { return remainingAmount; }
    public void setRemainingAmount(Double remainingAmount) { this.remainingAmount = remainingAmount; }

    public StaffInfoDTO getStaff() { return staff; }
    public void setStaff(StaffInfoDTO staff) { this.staff = staff; }
}
