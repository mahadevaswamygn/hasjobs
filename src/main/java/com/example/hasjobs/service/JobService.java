package com.example.hasjobs.service;

import com.example.hasjobs.entity.Job;
import com.example.hasjobs.repository.CompanyRepository;
import com.example.hasjobs.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {


    @Autowired
    JobRepository jobRepository;

    public void saveJob(Job job) {
        jobRepository.save(job);

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

    public List<Job> filterJobs(String location, String type, String category) {
        List<Job> allJobs = jobRepository.findAll();
        if (location != null) {
            boolean typeFilter = false;
            List<Job> filteredByLocation = new ArrayList<>();
            List<Job> filteredByLocationAndType = new ArrayList<>();
            for (Job job : allJobs) {
                if (job.getLocation().equals(location)) {
                    filteredByLocation.add(job);
                }
            }
            if(filteredByLocation!=null) {
                return filteredByLocation;
            }
            if (type != null) {
                typeFilter = true;
                for (Job job : filteredByLocation) {
                    if (job.getType().equals(type)) {
                        filteredByLocationAndType.add((job));
                    }
                }
            }
            if (typeFilter == false) {
                if (category != null) {
                    List<Job> filteredByLocationAndCategory = new ArrayList<>();
                    for (Job job : filteredByLocation) {
                        if (job.getCategory().equals(category)) {
                            filteredByLocationAndCategory.add(job);
                        }
                    }
                    return filteredByLocationAndCategory;
                }

            } else {
                if (category != null) {
                    List<Job> filteredByLocationTypeAndCategory = new ArrayList<>();
                    for (Job job : filteredByLocationAndType) {
                        if ((job.getCategory().equals(category))) {
                            filteredByLocationTypeAndCategory.add(job);
                        }
                    }
                    return filteredByLocationTypeAndCategory;
                }
                return filteredByLocationAndType;
            }
            return filteredByLocation;
        }
        return allJobs;
    }
}
