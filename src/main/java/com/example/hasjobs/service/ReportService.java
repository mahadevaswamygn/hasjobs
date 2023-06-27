package com.example.hasjobs.service;

import com.example.hasjobs.entity.Report;
import com.example.hasjobs.entity.User;
import com.example.hasjobs.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;
    public void saveReport(User user, String massage) {
        Report report=new Report();
        report.setReportMassage(massage);
        report.setUser(user);
        reportRepository.save(report);
    }
}
