package com.tvm.staff_management_system.dto;

// StaffInfoDTO.java

public class StaffInfoDTO {
    private Long staffId;
    private String name;

    public StaffInfoDTO(Long staffId, String name) {
        this.staffId = staffId;
        this.name = name;
    }


    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


}

