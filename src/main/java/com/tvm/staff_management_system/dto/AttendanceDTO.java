package com.tvm.staff_management_system.dto;

import com.tvm.staff_management_system.model.AttendanceStatus;

import java.time.LocalDateTime;

public class AttendanceDTO {

    private Long attendanceId;
    private StaffInfoDTO staff;      // nested staff info
    private String status;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String remarks;





    //  Constructor
    public AttendanceDTO(Long attendanceId, StaffInfoDTO staff, String status,
                         LocalDateTime checkInTime, LocalDateTime checkOutTime, String remarks) {
        this.attendanceId = attendanceId;
        this.staff = staff;
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.remarks = remarks;
    }

    // Getters & Setters
    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }

    public StaffInfoDTO getStaff() { return staff; }
    public void setStaff(StaffInfoDTO staff) { this.staff = staff; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }

    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalDateTime checkOutTime) { this.checkOutTime = checkOutTime; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
