package com.ats.applicantTrackingSystem.Services;

import com.ats.applicantTrackingSystem.DTO.InterviewDTO;
import com.ats.applicantTrackingSystem.Models.Application;
import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.Interview;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;
import com.ats.applicantTrackingSystem.Repository.ApplicationRepository;
import com.ats.applicantTrackingSystem.Repository.InterviewRepository;
import com.ats.applicantTrackingSystem.Repository.JobPostingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepo;
    @Autowired
    private JobPostingsRepository jobRepo;
    @Autowired
    private ApplicationRepository appRepo;

    public Interview createInterview(InterviewDTO dto) {
        CompositePrimaryKeyConfig jobKey = new CompositePrimaryKeyConfig(dto.getJobId(), dto.getCompanyName(), dto.getRecruiterId());
        JobPostDetails job = jobRepo.findById(jobKey).orElseThrow();
        Application app = appRepo.findById(dto.getApplicationId()).orElseThrow();

        Interview interview = new Interview();
        interview.setJob(job);
        interview.setApplication(app);
        interview.setScheduledTime(dto.getScheduledTime());
        interview.setInterviewer(dto.getInterviewer());
        interview.setNotes(dto.getNotes());
        interview.setStatus(dto.getStatus());

        return interviewRepo.save(interview);
    }

    public Interview updateInterview(Long id, InterviewDTO dto) {
        Interview interview = interviewRepo.findById(id).orElseThrow();
        // update fields if present in dto
        if (dto.getScheduledTime() != null) interview.setScheduledTime(dto.getScheduledTime());
        if (dto.getInterviewer() != null) interview.setInterviewer(dto.getInterviewer());
        if (dto.getNotes() != null) interview.setNotes(dto.getNotes());
        if (dto.getStatus() != null) interview.setStatus(dto.getStatus());
        return interviewRepo.save(interview);
    }

    public void deleteInterview(Long id) {
        interviewRepo.deleteById(id);
    }

    public List<Interview> getInterviewsByRecruiter(Long recruiterId) {
        return interviewRepo.findByJob_Id_RecruiterId(recruiterId);
    }

    public List<Interview> getInterviewsByJob(String jobId, String companyName, Long recruiterId) {
        return interviewRepo.findByJob_Id_JobIdAndJob_Id_CompanyNameAndJob_Id_RecruiterId(jobId, companyName, recruiterId);
    }

    public List<Interview> getInterviewsByCandidate(Long applicationId) {
        return interviewRepo.findByApplication_Id(applicationId);
    }
}
