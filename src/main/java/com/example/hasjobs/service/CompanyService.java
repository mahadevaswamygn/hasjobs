package com.example.hasjobs.service;

import com.example.hasjobs.entity.Company;
import com.example.hasjobs.entity.Employee;
import com.example.hasjobs.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public Company saveCompany(String companyName, String url, MultipartFile logo, Employee employee1, String email) {
        Company company=new Company();
        company.setName(companyName);
        company.setUrl(url);
        byte[] logoBytes = null;
        if (logo != null && !logo.isEmpty()) {
            try {
                logoBytes = logo.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        company.setLogo(logoBytes);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        company.setEmployee(employeeList);
        company.setEmail(email);
        return companyRepository.save(company);
    }
}
