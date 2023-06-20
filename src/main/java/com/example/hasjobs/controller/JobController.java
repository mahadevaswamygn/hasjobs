package com.example.hasjobs.controller;

import com.example.hasjobs.entity.Company;
import com.example.hasjobs.entity.Employee;
import com.example.hasjobs.entity.Job;
import com.example.hasjobs.entity.JobForm;
import com.example.hasjobs.service.CompanyService;
import com.example.hasjobs.service.EmployeeService;
import com.example.hasjobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CompanyService companyService;


    @PostMapping(value = "/post-job")
    public String addJob(@RequestParam("headline") String headline,
                         @RequestParam("type") String type,
                         @RequestParam("category") String category,
                         @RequestParam("location") String location,
                         @RequestParam(value = "relocation", required = false) boolean relocation,
                         @RequestParam("description") String description,
                         @RequestParam(value = "perks", required = false) boolean perks,
                         @RequestParam("perksdescription") String perksDescription,
                         @RequestParam("pay") String pay,
                         @RequestParam(value = "equity", required = false) boolean equity,
                         @RequestParam("submission") String submission,
                         @RequestParam("employer-name") String employerName,
                         @RequestParam(value = "logo", required = false) MultipartFile logo,
                         @RequestParam("url") String url,
                         @RequestParam("email") String email,
                         @RequestParam("collaborators") String collaborators,
                         @RequestParam("recruiters") String recruiters,
                         @RequestParam("salary") int salary,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        Employee employee=new Employee();
        employee.setName(employerName);
        employee.setEmail(email);
        Employee employee1= employeeService.save(employee);

        Company company=new Company();
        company.setName(extractCompanyName(url));
        company.setUrl(url);
        System.out.println(logo);
        byte[] logoBytes = null;
        if (logo != null && !logo.isEmpty()) {
            try {
                logoBytes = logo.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(logoBytes);
        company.setLogo(logoBytes);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        company.setEmployee(employeeList);
        company.setEmail(email);
        Company company1=companyService.save(company);


        Job job=new Job();
        job.setHeadline(headline);
        job.setType(type);
        job.setCategory(category);
        job.setLocation(location);
        job.setJobPerks(perksDescription);
        job.setSalary(salary);
        job.setCompany(company1);
        jobService.saveJob(job);



        return "home";
    }
    public String extractCompanyName(String url) {
        String trimmedUrl = url.replaceAll("www\\.", "").replaceAll("\\.com", "");
        return trimmedUrl;
    }

    @GetMapping(value = "/home")
    public String home(){
        return "index";
    }

    @GetMapping(value = "/new")
    public String newJob()
    {
        return "post-job";
    }

}
