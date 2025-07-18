package com.ats.applicantTrackingSystem.DTO;

import com.ats.applicantTrackingSystem.Models.ENUM.ApplicationStatus;
import com.ats.applicantTrackingSystem.Models.Interview;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;

import java.util.List;

public class CandidateJobDTO {
    private JobPostDetails job;
    private ApplicationStatus status;
    private List<Interview> interviews;

    public CandidateJobDTO() {
    }

    public CandidateJobDTO(JobPostDetails job, ApplicationStatus status, List<Interview> interviews) {
        this.job = job;
        this.status = status;
        this.interviews = interviews;
    }

    public JobPostDetails getJob() {
        return job;
    }

    public void setJob(JobPostDetails job) {
        this.job = job;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
}

