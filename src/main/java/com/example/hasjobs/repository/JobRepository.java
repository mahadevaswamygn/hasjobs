package com.example.hasjobs.repository;

import com.example.hasjobs.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {
    @Query("SELECT j FROM Job j WHERE LOWER(j.headline) LIKE %:searchQuery% OR LOWER(j.type) LIKE %:searchQuery% OR LOWER(j.Category) LIKE %:searchQuery% OR LOWER(j.location) LIKE %:searchQuery% OR LOWER(j.description) LIKE %:searchQuery%")
    List<Job> searchJobs(String searchQuery);
}
