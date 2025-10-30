package com.tvm.staff_management_system.dto;



import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
public class AdvanceDTO {
    private Long id;
    private LocalDate date;
    private Double amount;

    private StaffInfoDTO staff;


    public AdvanceDTO(Long id, LocalDate date, Double amount, StaffInfoDTO staff) {
        this.id = id;
        this.date = date;
        this.amount = amount;

        this.staff = staff;
    }


    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }


    public StaffInfoDTO getStaff() { return staff; }
    public void setStaff(StaffInfoDTO staff) { this.staff = staff; }
}

