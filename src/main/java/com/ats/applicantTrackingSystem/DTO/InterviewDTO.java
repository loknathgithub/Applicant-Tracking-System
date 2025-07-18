package com.ats.applicantTrackingSystem.DTO;

import com.ats.applicantTrackingSystem.Models.ENUM.InterviewStatus;

import java.time.LocalDateTime;

public class InterviewDTO {
    private String jobId;
    private String companyName;
    private Long recruiterId;
    private Long applicationId;
    private LocalDateTime scheduledTime;
    private String interviewer;
    private String notes;
    private InterviewStatus status;

    public InterviewDTO(){
    }

    public InterviewDTO(String jobId, String companyName, Long recruiterId, Long applicationId,
                        LocalDateTime scheduledTime, String interviewer, String notes, InterviewStatus status) {
        this.jobId = jobId;
        this.companyName = companyName;
        this.recruiterId = recruiterId;
        this.applicationId = applicationId;
        this.scheduledTime = scheduledTime;
        this.interviewer = interviewer;
        this.notes = notes;
        this.status = status;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }
}

