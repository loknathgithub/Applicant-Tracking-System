package com.ats.applicantTrackingSystem.Models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;

@Entity(name = "role_details")
public class JobRoleMatcher {
    @EmbeddedId
    CompositePrimaryKeyConfig id;
    @NotNull
    private String jobTitle;
    @NotNull
    private String jobLocation;
    @NotNull
    private String jobDescription;
    @NotNull
    private String jobQualification;
    @NotNull
    @Column(nullable = false)
    private HashMap<String, Integer> skills;

    //optional fields
    private HashMap<String, Integer> optionalSkills;

    public JobRoleMatcher() {
    }

    public JobRoleMatcher(CompositePrimaryKeyConfig id, String jobTitle,
                          String jobLocation, String jobDescription, String jobQualification,
                          HashMap<String, Integer> skills, HashMap<String, Integer> optionalSkills) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobQualification = jobQualification;
        this.skills = skills;
        this.optionalSkills = optionalSkills;
    }

    public CompositePrimaryKeyConfig getId() {
        return id;
    }

    public void setId(CompositePrimaryKeyConfig id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobQualification() {
        return jobQualification;
    }

    public void setJobQualification(String jobQualification) {
        this.jobQualification = jobQualification;
    }

    public HashMap<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(HashMap<String, Integer> skills) {
        this.skills = skills;
    }

    public HashMap<String, Integer> getOptionalSkills() {
        return optionalSkills;
    }

    public void setOptionalSkills(HashMap<String, Integer> optionalSkills) {
        this.optionalSkills = optionalSkills;
    }
}