package com.tvm.staff_management_system.dto;



import com.tvm.staff_management_system.model.AttendanceStatus;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CheckInDTO {
    private Long staffId;
    private AttendanceStatus status;
    private String remarks;

    // Getters & Setters
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}

