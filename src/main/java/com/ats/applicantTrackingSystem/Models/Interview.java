package com.ats.applicantTrackingSystem.Models;

import com.ats.applicantTrackingSystem.Models.ENUM.InterviewStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Interview {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime scheduledTime;
    private String interviewer;
    private String notes;
    @Enumerated(EnumType.STRING)
    private InterviewStatus status;

    @ManyToOne
    private JobPostDetails job;

    @ManyToOne
    private Application application;

    public Interview() {
    }

    public Interview(Long id, LocalDateTime scheduledTime, String interviewer, String notes, InterviewStatus status, JobPostDetails job, Application application) {
        this.id = id;
        this.scheduledTime = scheduledTime;
        this.interviewer = interviewer;
        this.notes = notes;
        this.status = status;
        this.job = job;
        this.application = application;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public JobPostDetails getJob() {
        return job;
    }

    public void setJob(JobPostDetails job) {
        this.job = job;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}