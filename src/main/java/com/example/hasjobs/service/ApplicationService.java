package com.example.hasjobs.service;

import com.example.hasjobs.entity.Application;
import com.example.hasjobs.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }
}
