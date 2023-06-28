package com.example.hasjobs.service;

import com.example.hasjobs.entity.Application;
import com.example.hasjobs.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    public void saveApplication(int id, int userId, String description) {
        Application application = new Application();
        application.setJobId(id);
        application.setUserId(userId);
        application.setDescription(description);
        applicationRepository.save(application);
    }
}
