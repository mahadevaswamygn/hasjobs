package com.example.hasjobs.service;

import com.example.hasjobs.entity.Collaborator;
import com.example.hasjobs.repository.CollaboratorRepository;
import com.example.hasjobs.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollaboratorService {


    @Autowired
    CollaboratorRepository collaboratorRepository;

    public List<Collaborator> getCollaborators(String[] collaboratorsNames) {
        List<Collaborator> collaborators = new ArrayList<>();
        for (String name : collaboratorsNames) {
            Collaborator collaborator = collaboratorRepository.findByName(name);
            if (collaborator != null) {
                collaborators.add(collaborator);
            }
        }
        return collaborators;
    }
}
