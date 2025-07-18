package com.ats.applicantTrackingSystem.Models;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositePrimaryKeyConfig implements Serializable {
    @Column(nullable = false)
    private String jobID;
    @Column(nullable = false)
    private String jobCompanyName;
    @Column(nullable = false)
    private Long recruiterID;

    public CompositePrimaryKeyConfig() {
    }

    public CompositePrimaryKeyConfig(String jobID, String jobCompanyName, Long recruiterID) {
        this.jobID = jobID;
        this.jobCompanyName = jobCompanyName;
        this.recruiterID = recruiterID;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getJobCompanyName() {
        return jobCompanyName;
    }

    public void setJobCompanyName(String jobCompanyName) {
        this.jobCompanyName = jobCompanyName;
    }

    public Long getRecruiterID() {
        return recruiterID;
    }

    public void setRecruiterID(Long recruiterID) {
        this.recruiterID = recruiterID;
    }

    @Override
    public String toString() {
        return "CompositePrimaryKeyConfig{" +
                "jobID='" + jobID + '\'' +
                ", jobCompanyName='" + jobCompanyName + '\'' +
                ", recruiterID='" + recruiterID + '\'' +
                '}';
    }
}
