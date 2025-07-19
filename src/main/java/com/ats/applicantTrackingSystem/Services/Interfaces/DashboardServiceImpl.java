package com.ats.applicantTrackingSystem.Services.Interfaces;

import com.ats.applicantTrackingSystem.DTO.JobDashboardDTO;
import java.util.List;

public interface DashboardServiceImpl {
        public List<JobDashboardDTO> getRecruiterDashboard(Long recruiterId);
}

