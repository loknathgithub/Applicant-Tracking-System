package com.ats.applicantTrackingSystem.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositePrimaryKeyConfig implements Serializable {
    @Column(nullable = false)
    private String jobID;

    @Column(nullable = false)
    private String jobCompanyName;

    @Column(nullable = false)
    private Long recruiterId;

    public CompositePrimaryKeyConfig() {
    }

    public CompositePrimaryKeyConfig(String jobID, String jobCompanyName, Long recruiterId) {
        this.jobID = jobID;
        this.jobCompanyName = jobCompanyName;
        this.recruiterId = recruiterId;
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

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositePrimaryKeyConfig)) return false;
        CompositePrimaryKeyConfig that = (CompositePrimaryKeyConfig) o;
        return Objects.equals(jobID, that.jobID)
                && Objects.equals(jobCompanyName, that.jobCompanyName)
                && Objects.equals(recruiterId, that.recruiterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobID, jobCompanyName, recruiterId);
    }

    @Override
    public String toString() {
        return "CompositePrimaryKeyConfig{" +
                "jobID='" + jobID + '\'' +
                ", jobCompanyName='" + jobCompanyName + '\'' +
                ", recruiterId=" + recruiterId +
                '}';
    }
}