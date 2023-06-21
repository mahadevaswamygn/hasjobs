package com.example.hasjobs.service;

import com.example.hasjobs.entity.Job;
import com.example.hasjobs.repository.CompanyRepository;
import com.example.hasjobs.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Job> searchedJobs=jobRepository.searchJobs(searchQuery);
        return searchedJobs;
    }
}
