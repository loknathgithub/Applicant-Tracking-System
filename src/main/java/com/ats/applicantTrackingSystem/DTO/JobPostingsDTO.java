package com.ats.applicantTrackingSystem.DTO;
import com.ats.applicantTrackingSystem.Models.WorkType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class JobPostingsDTO {
    private String jobID;
    private String jobCompanyName;
    private String jobTitle;
    private String jobLocation;
    private String jobDescription;
    private String jobQualification;
    private String jobContactMethod;
    private WorkType jobWorkType;
    private List<String> skills;
    private List<String> optionalSkills=null;
    private Integer minSalary = null;
    private Integer maxSalary= null;
    private String perks= null;
    private String startDate = null;
    private LocalDate deadline= null;

    public JobPostingsDTO() {
    }

    public JobPostingsDTO(String jobID, String jobCompanyName, String jobTitle, String jobLocation,String jobDescription,
                          String jobQualification, String jobContactMethod, WorkType jobWorkType, List<String> skills, List<String> optionalSkills) {
        this.jobID = jobID;
        this.jobCompanyName = jobCompanyName;
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobQualification = jobQualification;
        this.jobContactMethod = jobContactMethod;
        this.jobWorkType = jobWorkType;
        this.skills=skills;
        this.optionalSkills=optionalSkills;
    }

    public JobPostingsDTO(String jobID, String jobCompanyName, String jobTitle, String jobLocation,
                          String jobDescription, String jobQualification, String jobContactMethod, WorkType jobWorkType,
                          List<String> skills, List<String> optionalSkills, Integer minSalary, Integer maxSalary,
                          String perks, String startDate, LocalDate deadline) {
        this.jobID = jobID;
        this.jobCompanyName = jobCompanyName;
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobQualification = jobQualification;
        this.jobContactMethod = jobContactMethod;
        this.jobWorkType = jobWorkType;
        this.skills = skills;
        this.optionalSkills = optionalSkills;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.perks = perks;
        this.startDate = startDate;
        this.deadline = deadline;
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

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getPerks() {
        return perks;
    }

    public void setPerks(String perks) {
        this.perks = perks;
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

}
