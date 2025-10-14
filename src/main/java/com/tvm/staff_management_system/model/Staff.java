package com.tvm.staff_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private Double salary;
    private LocalDate joinDate;
    private String status;
//
//    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties("staff") // prevents infinite recursion in JSON
//    private List<Attendance> attendances = new ArrayList<>();
//
//    @ManyToOne
//    @JoinColumn(name = "staff_id")
//    @JsonIgnoreProperties("attendances") // avoids recursion
//    private Staff staff;


    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AdvanceSalary> advanceList;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SalaryPayment> salaryPaymentList;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
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

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public List<AdvanceSalary> getAdvanceList() {
        return advanceList;
    }

    public void setAdvanceList(List<AdvanceSalary> advanceList) {
        this.advanceList = advanceList;
    }

    public List<SalaryPayment> getSalaryPaymentList() {
        return salaryPaymentList;
    }

    public void setSalaryPaymentList(List<SalaryPayment> salaryPaymentList) {
        this.salaryPaymentList = salaryPaymentList;
    }
}
