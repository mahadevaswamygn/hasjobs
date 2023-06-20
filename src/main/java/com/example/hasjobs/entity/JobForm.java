package com.example.hasjobs.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class JobForm {
    private String headline;
    private String type;
    private String category;
    private String location;
    private String description;
    private boolean relocation;
    private boolean perks;
    private String pay;
    private boolean equity;
    private String submission;
    private String employerName;
    private MultipartFile logo;
    private String url;
    private String email;
    private String collaborators;
    private String recruiters;
    private String jobPerks;
}
