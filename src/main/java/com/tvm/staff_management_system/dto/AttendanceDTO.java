package com.tvm.staff_management_system.dto;

import java.time.LocalDate;

public class AttendanceDTO {
        private Long attendanceId;
        private LocalDate date;
        private String status;
        private String remarks;
        private StaffInfoDTO staff;

        public AttendanceDTO(Long attendanceId, LocalDate date, String status, String remarks, StaffInfoDTO staff) {
            this.attendanceId = attendanceId;
            this.date = date;
            this.status = status;
            this.remarks = remarks;
            this.staff = staff;
        }




    // getters and setters
        public Long getAttendanceId() { return attendanceId; }
        public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getRemarks() { return remarks; }
        public void setRemarks(String remarks) { this.remarks = remarks; }
        public StaffInfoDTO getStaff() { return staff; }
        public void setStaff(StaffInfoDTO staff) { this.staff = staff; }


}
