package com.ats.applicantTrackingSystem.Services.Interfaces;

import com.ats.applicantTrackingSystem.DTO.InterviewDTO;
import com.ats.applicantTrackingSystem.Models.Interview;
import java.util.List;

public interface InterviewServiceImpl {
    public Interview createInterview(InterviewDTO dto);
    public Interview updateInterview(Long id, InterviewDTO dto);
    public void deleteInterview(Long id);
    public List<Interview> getInterviewsByRecruiter(Long recruiterId);
    public List<Interview> getInterviewsByJob(String jobId, String companyName, Long recruiterId);
    public List<Interview> getInterviewsByCandidate(Long applicationId);
}
