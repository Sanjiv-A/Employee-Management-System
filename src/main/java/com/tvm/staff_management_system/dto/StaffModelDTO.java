package com.tvm.staff_management_system.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
public class StaffModelDTO {
    private String name;
    private String role;
    private Double baseSalary;
    private LocalDate joinDate;
    private String status;

    public StaffModelDTO(String name, String role, Double baseSalary, LocalDate joinDate, String status) {
        this.name = name;
        this.role = role;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
