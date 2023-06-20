package com.example.hasjobs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Collaborator {
    @Id
    private int id;
    private String name;
    private String hasGeekId;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}
