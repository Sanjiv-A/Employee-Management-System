package com.tvm.staff_management_system.dto;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CheckOutDTO {

    private String remarks;

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
