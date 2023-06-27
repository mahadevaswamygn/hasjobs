package com.example.hasjobs.repository;

import com.example.hasjobs.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {
}
