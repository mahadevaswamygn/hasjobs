package com.example.hasjobs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String headline;
    private String type;
    private String Category;
    private String location;
    private String description;
    private String jobPerks;
    private int salary;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "job")
    private List<Collaborator> collaboratorsList;
}
