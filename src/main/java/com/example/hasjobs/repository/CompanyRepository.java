package com.example.hasjobs.repository;

import com.example.hasjobs.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository  extends JpaRepository<Company,Integer> {
    Company findByName(String name);
}
