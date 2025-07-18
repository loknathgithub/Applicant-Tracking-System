package com.ats.applicantTrackingSystem.Services;

import com.ats.applicantTrackingSystem.DTO.JobDashboardDTO;
import com.ats.applicantTrackingSystem.Models.Application;
import com.ats.applicantTrackingSystem.Models.ENUM.ApplicationStatus;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;
import com.ats.applicantTrackingSystem.Repository.ApplicationRepository;
import com.ats.applicantTrackingSystem.Repository.JobPostingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    private JobPostingsRepository jobRepo;

    @Autowired
    private ApplicationRepository appRepo;

    public List<JobDashboardDTO> getRecruiterDashboard(Long recruiterId) {
        List<JobPostDetails> jobs = jobRepo.findByIdRecruiterId(recruiterId);
        List<JobDashboardDTO> result = new ArrayList<>();
        for (JobPostDetails job : jobs) {
            List<Application> applications = appRepo.findByJobPost_Id(job.getId());
            Map<ApplicationStatus, Long> statusCount = applications.stream()
                    .collect(Collectors.groupingBy(Application::getStatus, Collectors.counting()));
            result.add(new JobDashboardDTO(job, applications.size(), statusCount));
        }
        return result;
    }
}

