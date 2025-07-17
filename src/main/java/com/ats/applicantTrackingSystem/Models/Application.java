package com.ats.applicantTrackingSystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "job_application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @NotNull
    private ResumeDetails resume;

    private String coverLetter;

    private int score;

    public Application() {
    }

    public Application(Long applicationId, ResumeDetails resume, String coverLetter, int score) {
        this.applicationId = applicationId;
        this.resume = resume;
        this.coverLetter = coverLetter;
        this.score = score;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public ResumeDetails getResume() {
        return resume;
    }

    public void setResume(ResumeDetails resume) {
        this.resume = resume;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
