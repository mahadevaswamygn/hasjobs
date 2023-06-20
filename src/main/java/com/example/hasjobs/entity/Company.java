package com.example.hasjobs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Lob
    private byte[] logo;
    private String url;
    private String email;

    public byte[] getLogo() {
        return logo;
    }
    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    public String getLogoAsBase64() {
        if (logo != null && logo.length > 0) {
            return Base64.getEncoder().encodeToString(logo);
        }
        return null;
    }

    @OneToMany
    private List<Job> jobList;

    @OneToMany
    @JoinColumn(name = "company_id")
    private List<Employee> employee;
}
