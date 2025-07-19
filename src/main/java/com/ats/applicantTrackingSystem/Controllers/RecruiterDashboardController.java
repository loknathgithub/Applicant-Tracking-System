package com.ats.applicantTrackingSystem.Controllers;

import com.ats.applicantTrackingSystem.DTO.JobDashboardDTO;
import com.ats.applicantTrackingSystem.Services.Implementations.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class RecruiterDashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<List<JobDashboardDTO>> getRecruiterDashboard(@PathVariable Long recruiterId) {
        return ResponseEntity.ok(dashboardService.getRecruiterDashboard(recruiterId));
    }
}
