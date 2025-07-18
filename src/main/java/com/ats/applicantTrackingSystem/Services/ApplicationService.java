package com.ats.applicantTrackingSystem.Services;

import com.ats.applicantTrackingSystem.DTO.ApplicationDTO;
import com.ats.applicantTrackingSystem.Models.Application;
import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;
import com.ats.applicantTrackingSystem.Models.ResumeDetails;
import com.ats.applicantTrackingSystem.Repository.ApplicationRepository;
import com.ats.applicantTrackingSystem.Repository.JobPostingsRepository;
import com.ats.applicantTrackingSystem.Repository.ResumeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

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

        ResumeDetails resume = resumeDetailsRepository.findById(dto.getResumeId())
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        // 3. Create and populate Application entity
        Application app = new Application();
        app.setJobPost(job);
        app.setResume(resume);
        app.setCoverLetter(dto.getCoverLetter());
        // Optionally: app.setScore(...) if needed

        // 4. Persist new application
        return applicationRepository.save(app);
    }
}
