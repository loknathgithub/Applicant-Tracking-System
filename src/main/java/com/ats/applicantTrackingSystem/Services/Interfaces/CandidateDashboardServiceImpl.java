package com.ats.applicantTrackingSystem.Services.Interfaces;

import com.ats.applicantTrackingSystem.DTO.CandidateJobDTO;
import java.util.List;

public interface CandidateDashboardServiceImpl {
    public List<CandidateJobDTO> getCandidateDashboard(Long userId);
}