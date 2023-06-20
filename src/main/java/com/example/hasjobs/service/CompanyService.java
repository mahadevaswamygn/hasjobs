package com.example.hasjobs.service;

import com.example.hasjobs.entity.Company;
import com.example.hasjobs.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    public Company save(Company company) {
        Company company1=companyRepository.findByName(company.getName());
        if(company1!=null)
        {
            return company1;
        }
        Company company2=companyRepository.save(company);
        return company2;
    }
}
