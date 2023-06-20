package com.example.hasjobs.service;

import com.example.hasjobs.entity.Employee;
import com.example.hasjobs.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        Employee employee1=employeeRepository.findByName(employee.getName());
        if(employee1!=null){
            return employee1;
        }
        Employee employee2= employeeRepository.save(employee);
        return employee2;
    }
}
