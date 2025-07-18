package com.ats.applicantTrackingSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "job_application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @NotNull
    private ResumeDetails resume;

    private String coverLetter;

    private Integer score;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "job_id", referencedColumnName = "jobId"),
            @JoinColumn(name = "company_name", referencedColumnName = "companyName"),
            @JoinColumn(name = "recruiter_id", referencedColumnName = "recruiterId")
    })
    private JobPostDetails jobPost;    // foreign key


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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public JobPostDetails getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPostDetails jobPost) {
        this.jobPost = jobPost;
    }
}
