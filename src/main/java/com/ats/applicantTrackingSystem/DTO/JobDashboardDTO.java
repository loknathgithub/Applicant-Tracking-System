package com.ats.applicantTrackingSystem.DTO;

import com.ats.applicantTrackingSystem.Models.ENUM.ApplicationStatus;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;

import java.util.Map;

public class JobDashboardDTO {
    private JobPostDetails job;
    private int totalApplicants;
    private Map<ApplicationStatus, Long> applicantStatusCount;

    public JobDashboardDTO() {
    }

    public JobDashboardDTO(JobPostDetails job, int totalApplicants, Map<ApplicationStatus, Long> applicantStatusCount) {
        this.job = job;
        this.totalApplicants = totalApplicants;
        this.applicantStatusCount = applicantStatusCount;
    }

    public JobPostDetails getJob() {
        return job;
    }

    public void setJob(JobPostDetails job) {
        this.job = job;
    }

    public int getTotalApplicants() {
        return totalApplicants;
    }

    public void setTotalApplicants(int totalApplicants) {
        this.totalApplicants = totalApplicants;
    }

    public Map<ApplicationStatus, Long> getApplicantStatusCount() {
        return applicantStatusCount;
    }

    public void setApplicantStatusCount(Map<ApplicationStatus, Long> applicantStatusCount) {
        this.applicantStatusCount = applicantStatusCount;
    }
}
