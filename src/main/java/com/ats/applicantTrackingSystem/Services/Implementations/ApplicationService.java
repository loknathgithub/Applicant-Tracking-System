package com.ats.applicantTrackingSystem.Services.Implementations;

import com.ats.applicantTrackingSystem.DTO.ApplicationDTO;
import com.ats.applicantTrackingSystem.ExceptionHandlers.ResourceNotFoundException;
import com.ats.applicantTrackingSystem.Models.*;
import com.ats.applicantTrackingSystem.Models.ENUM.ApplicationStatus;
import com.ats.applicantTrackingSystem.Repository.ApplicationRepository;
import com.ats.applicantTrackingSystem.Repository.JobPostingsRepository;
import com.ats.applicantTrackingSystem.Repository.ResumeDetailsRepository;
import com.ats.applicantTrackingSystem.Services.Interfaces.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService implements ApplicationServiceImpl {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobPostingsRepository jobPostingsRepository;

    @Autowired
    private ResumeDetailsRepository resumeDetailsRepository;

    public Application processApplicationSubmission(ApplicationDTO dto) {
        // 1. Build the composite key for JobPostDetails
        CompositePrimaryKeyConfig jobKey = new CompositePrimaryKeyConfig(
                dto.getJobId(), dto.getJobCompanyName(), dto.getRecruiterId()
        );

        // 2. Fetch referenced entities
        JobPostDetails job = jobPostingsRepository.findById(jobKey)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));

        ResumeDetails resume = resumeDetailsRepository.findById(dto.getResumeId())   // checkpoint: finding resume in storage
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        // 3. Create and populate Application entity
        Application app = new Application();
        app.setJobPost(job);
        app.setResume(resume);
        app.setCoverLetter(dto.getCoverLetter());
        // Optionally: app.setScore(...)

        // 4. Persist new application
        return applicationRepository.save(app);
    }

    public ApplicationStatus getApplicationStatus(Long applicationId) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        return app.getStatus();
    }

    public ApplicationStatus updateApplicationStatus(Long applicationId, ApplicationStatus newStatus) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        app.setStatus(newStatus);
        applicationRepository.save(app);
        return app.getStatus();
    }

}
