package com.example.hasjobs.service;

import com.example.hasjobs.entity.Job;
import com.example.hasjobs.repository.CompanyRepository;
import com.example.hasjobs.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {


    @Autowired
    JobRepository jobRepository;
    public void saveJob(Job job) {
        jobRepository.save(job);

    }
}
