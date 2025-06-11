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

    public CompositePrimaryKeyConfig() {
    }

    public CompositePrimaryKeyConfig(String jobID, String jobCompanyName) {
        this.jobID = jobID;
        this.jobCompanyName = jobCompanyName;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompositePrimaryKeyConfig that = (CompositePrimaryKeyConfig) o;
        return Objects.equals(jobID, that.jobID) && Objects.equals(jobCompanyName, that.jobCompanyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobID, jobCompanyName);
    }
}
