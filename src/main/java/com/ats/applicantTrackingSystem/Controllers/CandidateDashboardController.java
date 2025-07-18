package com.ats.applicantTrackingSystem.Controllers;

import com.ats.applicantTrackingSystem.DTO.CandidateJobDTO;
import com.ats.applicantTrackingSystem.Services.CandidateDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class CandidateDashboardController {
    @Autowired
    private CandidateDashboardService dashboardService;

    @GetMapping("/candidate/{userId}")
    public ResponseEntity<List<CandidateJobDTO>> getCandidateDashboard(@PathVariable Long userId) {
        return ResponseEntity.ok(dashboardService.getCandidateDashboard(userId));
    }
}

