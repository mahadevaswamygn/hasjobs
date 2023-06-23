package com.example.hasjobs.service;

import com.example.hasjobs.entity.Job;
import com.example.hasjobs.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobService {


    @Autowired
    JobRepository jobRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);

    }

    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> searchJobs(String searchQuery) {
        List<Job> searchedJobs = jobRepository.searchJobs(searchQuery);
        return searchedJobs;
    }

    public List<String> findAllLocations() {
        List<String> allLocations = new ArrayList<>();
        List<Job> allJobs = jobRepository.findAll();
        for (Job job : allJobs) {
            if (!allLocations.contains(job.getLocation())) {
                allLocations.add(job.getLocation());
            }
        }
        return allLocations;
    }

    public List<String> findAllJobTypes() {
        List<String> allJobTypes = new ArrayList<>();
        List<Job> allJobs = jobRepository.findAll();
        for (Job job : allJobs) {
            if (!allJobTypes.contains(job.getType())) {
                allJobTypes.add(job.getType());
            }
        }
        return allJobTypes;
    }

    public List<Job> filterJobs(String location, String type, String category, String pay) {
        List<Job> allJobs = jobRepository.findAll();
        if (location != null) {
            if (location.length() <= 1) location = null;
        }
        if (location != null) {
            List<Job> filteredByLocation = new ArrayList<>();
            for (Job job : allJobs) {
                if (job.getLocation().equals(location)) {
                    filteredByLocation.add(job);
                }
            }
            if (filteredByLocation != null) {
                return filteredByLocation;
            }
        }
        if (type != null) {
            if (type.length() <= 1) type = null;
        }
        if (type != null) {
            List<Job> filteredByType = new ArrayList<>();
            for (Job job : allJobs) {
                if (job.getType().equals(type)) {
                    filteredByType.add(job);
                }
            }
            return filteredByType;
        }
        if (category != null) {
            if (category.length() <= 1) category = null;
        }
        if (category != null) {
            String filterCategory=category.toLowerCase().trim();
            List<Job> filteredByCategory = new ArrayList<>();
            for (Job job : allJobs) {
                if (filterCategory.equals(job.getCategory().toLowerCase().trim())) {
                    filteredByCategory.add(job);
                }
            }
            return filteredByCategory;
        }
        if (pay != null) {
            List<Job> filteredByPay = new ArrayList<>();
            for (Job job : allJobs) {
                if (job.getSalary() == (Integer.parseInt(pay))) {
                    filteredByPay.add(job);
                }
            }
            return filteredByPay;
        }
        return null;
    }

    public Job findJobById(int id) {
        Job job = jobRepository.findById(id).get();
        return job;
    }

    public String findTrendingTechnology() {
        List<Job> allJobs = jobRepository.findAll();
        HashMap<String, Integer> trendingTechnology = new HashMap<>();
        for (Job job : allJobs) {
            if (trendingTechnology.containsKey(job.getCategory())) {
                int numberOfOpenings = trendingTechnology.get(job.getCategory());
                trendingTechnology.put(job.getCategory(), numberOfOpenings + 1);
            } else {
                trendingTechnology.put(job.getCategory(), 1);
            }
        }
        int numberOfOpenings = 0;
        String technology = "";
        for (Map.Entry<String, Integer> technologyStoredMap : trendingTechnology.entrySet()) {
            if (numberOfOpenings < technologyStoredMap.getValue()) {
                technology = technologyStoredMap.getKey();
            }
        }
        return technology;
    }

    public List<String> findAllSalary() {
        List<Job> allJobs = jobRepository.findAll();
        List<String> allSalary = new ArrayList<>();
        for (Job job : allJobs) {
            if (!allSalary.contains(Integer.toString(job.getSalary()))) {
                allSalary.add(Integer.toString(job.getSalary()));
            }
        }
        return allSalary;
    }
}
