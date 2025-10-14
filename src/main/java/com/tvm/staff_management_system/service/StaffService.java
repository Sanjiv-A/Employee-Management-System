package com.tvm.staff_management_system.service;


import com.tvm.staff_management_system.model.Staff;
import com.tvm.staff_management_system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Staff save(Staff staff){
        return  staffRepository.save(staff);
    }
    public List<Staff> getAllStaff(){
        return staffRepository.findAll();
    }
    public Optional<Staff> getById(Long id){

        return staffRepository.findById(id);

    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElseThrow(()-> new RuntimeException("Staff Not Found With " +id +" View" ));
    }
    public void deleteStaffById(Long id){
        if(staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
        }
        else throw new RuntimeException("Staff Not Found With "+id + " to Delete");

    }
    public Staff updateStaffById(Long id, Staff staff) {
        return staffRepository.findById(id)
                .map(existing -> {
                    existing.setName(staff.getName());
                    existing.setRole(staff.getRole());
                    existing.setSalary(staff.getSalary());
                    existing.setJoinDate(staff.getJoinDate());
                    existing.setStatus(staff.getStatus());
                    return staffRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + id));
    }



}
