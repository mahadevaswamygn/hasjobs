package com.example.hasjobs.repository;

import com.example.hasjobs.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findByName(String name);
}
