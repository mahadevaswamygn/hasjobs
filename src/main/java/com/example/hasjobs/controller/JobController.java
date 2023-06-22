package com.example.hasjobs.controller;

import com.example.hasjobs.entity.*;
import com.example.hasjobs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    CollaboratorService collaboratorService;

    @GetMapping(value = "/")
    public String homePage(){
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        List<Job> allJobs = jobService.findAllJobs();
        model.addAttribute("allJobs", allJobs);
        List<String> allLocations = jobService.findAllLocations();
        model.addAttribute("allLocations", allLocations);
        List<String> allTypes = jobService.findAllJobTypes();
        model.addAttribute("allTypes", allTypes);
        String trendingTechnology = jobService.findTrendingTechnology();
        model.addAttribute("trendingTechnology", trendingTechnology);
        List<String> allSalary = jobService.findAllSalary();
        model.addAttribute("allSalary", allSalary);
        return "index";
    }


    @PostMapping(value = "/post-job")
    public String addJob(@RequestParam("headline") String headline,
                         @RequestParam("type") String type,
                         @RequestParam("category") String category,
                         @RequestParam("location") String location,
                         @RequestParam(value = "relocation", required = false) boolean relocation,
                         @RequestParam("description") String description,
                         @RequestParam(value = "perks", required = false) boolean perks,
                         @RequestParam("perksDescription") String perksDescription,
                         @RequestParam("pay") String paymentType,
                         @RequestParam(value = "paymentInfo", required = false) String paymentInfo,
                         @RequestParam(value = "selectedPrice", required = false) Integer selectedPrice,
                         @RequestParam(value = "equity", required = false) boolean equity,
                         @RequestParam("submission") String submission,
                         @RequestParam("employer-name") String employerName,
                         @RequestParam(value = "logo", required = false) MultipartFile logo,
                         @RequestParam("url") String url,
                         @RequestParam("email") String email,
                         @RequestParam("collaborators") String collaborators,
                         @RequestParam("recruiters") String recruiters,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (selectedPrice != null) {
            System.out.println(selectedPrice.intValue());
        }

        Employee employee = new Employee();
        employee.setName("lahari");
        Employee employee1 = employeeService.save(employee);
        Company company = new Company();
        company.setName(employerName);
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
        Company company1 = companyService.save(company);
        Job job = new Job();
        job.setHeadline(headline);
        job.setType(type);
        job.setCategory(category);
        job.setLocation(location);
        job.setJobPerks(perksDescription);
        job.setDescription(description);
        if (selectedPrice != null) {
            job.setSalary(selectedPrice.intValue());
        } else {
            job.setSalary(0);
        }
        job.setCompany(company1);
        job.setPoster("lahari");
        job.setPostedDate(new Date());
        Job newJob = jobService.saveJob(job);
        String[] collaboratorsNames = collaborators.split(",");
        List<Collaborator> collaboratorsList = collaboratorService.getCollaborators(collaboratorsNames);
        job.setCollaboratorsList(collaboratorsList);
        model.addAttribute("job", newJob);
        return "review";
    }

    @GetMapping(value = "/new")
    public String newJob(Model model) {
        List<String> allLocations = jobService.findAllLocations();
        model.addAttribute("allLocations", allLocations);
        List<String> allTypes = jobService.findAllJobTypes();
        model.addAttribute("allTypes", allTypes);
        List<String> allSalary = jobService.findAllSalary();
        model.addAttribute("allSalary", allSalary);
        return "add-job";
    }

    @GetMapping("/search")
    public String handleSearchRequest(@RequestParam("search") String searchQuery, Model model) {
        List<Job> searchedJobs = jobService.searchJobs(searchQuery);
        model.addAttribute("searchedJobs", searchedJobs);
        model.addAttribute("searchQuery", searchQuery);
        return "searched";
    }

    @GetMapping(value = "/filter")
    public String filter(@RequestParam(value = "location", required = false) String location,
                         @RequestParam(value = "type", required = false) String type,
                         @RequestParam(value = "category", required = false) String category,
                         @RequestParam(value = "pay", required = false) String pay,
                         Model model) {
        List<Job> filteredJobs = jobService.filterJobs(location, type, category, pay);
        model.addAttribute("filteredJobs", filteredJobs);
        List<String> allLocations = jobService.findAllLocations();
        model.addAttribute("allLocations", allLocations);
        List<String> allTypes = jobService.findAllJobTypes();
        model.addAttribute("allTypes", allTypes);
        List<String> allSalary = jobService.findAllSalary();
        model.addAttribute("allSalary", allSalary);

        return "filtered";
    }

    @GetMapping(value = "/show-job/{id}")
    public String showJob(@PathVariable int id,
                          Model model) {
        Job job = jobService.findJobById(id);
        model.addAttribute("job", job);
        model.addAttribute("id", id);
        return "show-job";
    }

    @PostMapping(value = "/apply-job/{id}")
    public String applyJob(@PathVariable int id,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("phone") String phone,
                           @RequestParam("description") String description) {

        User user = userService.findUseByEmail(email);
        if (user == null) {
            user = userService.saveUser(name, email, phone);
        }
        Application application = new Application();
        application.setJobId(id);
        application.setUserId(user.getId());
        application.setDescription(description);
        applicationService.saveApplication(application);
        return "redirect:/home";
    }

    @GetMapping(value = "/interview-preparation")
    public String interviewPreparation() {
        return "interview-preparation-page";
    }
}
