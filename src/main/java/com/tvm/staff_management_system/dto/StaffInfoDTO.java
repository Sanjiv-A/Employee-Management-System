package com.tvm.staff_management_system.dto;

import lombok.NoArgsConstructor;

// StaffInfoDTO.java

public class StaffInfoDTO {
    private Long staffId;
    private String name;
    private String role;
    private Double baseSalary;

    public StaffInfoDTO(Long staffId, String name, String role, Double baseSalary) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.baseSalary = baseSalary;
    }
    public StaffInfoDTO() {

    }

    public StaffInfoDTO(Long id, String name) {
        this.staffId = id;
        this.name = name; // assign name properly
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
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
}


