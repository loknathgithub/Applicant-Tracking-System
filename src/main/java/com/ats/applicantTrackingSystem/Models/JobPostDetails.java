package com.ats.applicantTrackingSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity(name="job_listings")
public class JobPostDetails {

    //required fields
    @EmbeddedId
    private CompositePrimaryKeyConfig id;

    @NotNull
    private String jobTitle;
    @NotNull
    private String jobLocation;
    @NotNull
    private String jobDescription;
    @NotNull
    private String jobQualification;
    @NotNull
    private String jobContactMethod;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkType jobWorkType;
    @NotNull
    @Column(nullable = false)
    private List<String> skills;

    //optional fields
    private List<String> optionalSkills;  // set limit in pgadmin by adding a check constraint
    private Integer minSalary;
    private Integer maxSalary;
    private String perks;
    private String startDate;
    private LocalDate deadline;

    public JobPostDetails() {
    }

    public JobPostDetails(CompositePrimaryKeyConfig id, String jobTitle, String jobLocation,
                          String jobDescription, String jobQualification, String jobContactMethod,
                          WorkType jobWorkType, List<String> skills, List<String> optionalSkills){
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobQualification = jobQualification;
        this.jobContactMethod = jobContactMethod;
        this.jobWorkType = jobWorkType;
        this.skills = skills;
        this.optionalSkills = optionalSkills;
    }
    public JobPostDetails(CompositePrimaryKeyConfig id, String jobTitle, String jobLocation,
                          String jobDescription, String jobQualification, String jobContactMethod,
                          WorkType jobWorkType, Integer minSalary, Integer maxSalary, String perks,
                          List<String> skills, List<String> optionalSkills, String startDate, LocalDate deadline) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobQualification = jobQualification;
        this.jobContactMethod = jobContactMethod;
        this.jobWorkType = jobWorkType;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.perks = perks;
        this.startDate = startDate;
        this.deadline = deadline;
        this.skills = skills;
        this.optionalSkills = optionalSkills;

    }


    public CompositePrimaryKeyConfig getId() {
        return id;
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

    public String getJobContactMethod() {
        return jobContactMethod;
    }

    public void setJobContactMethod(String jobContactMethod) {
        this.jobContactMethod = jobContactMethod;
    }

    public WorkType getJobWorkType() {
        return jobWorkType;
    }

    public void setJobWorkType(WorkType jobWorkType) {
        this.jobWorkType = jobWorkType;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getPerks() {
        return perks;
    }

    public void setPerks(String perks) {
        this.perks = perks;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getOptionalSkills() {
        return optionalSkills;
    }

    public void setOptionalSkills(List<String> optionalSkills) {
        this.optionalSkills = optionalSkills;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "JobPostDetails{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobLocation='" + jobLocation + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobQualification='" + jobQualification + '\'' +
                ", jobContactMethod='" + jobContactMethod + '\'' +
                ", jobWorkType=" + jobWorkType +
                ", skills=" + skills +
                ", optionalSkills=" + optionalSkills +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", perks='" + perks + '\'' +
                ", startDate='" + startDate + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
