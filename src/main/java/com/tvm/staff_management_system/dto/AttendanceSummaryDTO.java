package com.tvm.staff_management_system.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class AttendanceSummaryDTO {
    private StaffInfoDTO staff;
    private long totalWorkedDays;
    private long totalLeaves;

    public AttendanceSummaryDTO(StaffInfoDTO staff, long totalWorkedDays, long totalLeaves) {
        this.staff = staff;
        this.totalWorkedDays = totalWorkedDays;
        this.totalLeaves = totalLeaves;
    }

    public StaffInfoDTO getStaff() { return staff; }
    public void setStaff(StaffInfoDTO staff) { this.staff = staff; }

    public long getTotalWorkedDays() { return totalWorkedDays; }
    public void setTotalWorkedDays(long totalWorkedDays) { this.totalWorkedDays = totalWorkedDays; }

    public long getTotalLeaves() { return totalLeaves; }
    public void setTotalLeaves(long totalLeaves) { this.totalLeaves = totalLeaves; }
}

