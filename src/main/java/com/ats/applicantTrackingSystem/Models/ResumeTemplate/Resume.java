package com.ats.applicantTrackingSystem.Models.ResumeTemplate;
// Resume.java

import jakarta.persistence.ElementCollection;

import java.util.List;
public class Resume {

    private String name;
    private String email;
    private String phone;
    private String location;
    private String summary;
    private List<String> skills;

    @ElementCollection
    private List<Education> education;

    @ElementCollection
    private List<Experience> experience;

    @ElementCollection
    private List<Certification> certifications;

    @ElementCollection
    private List<Project> projects;

    public Resume() {
    }

    public Resume(String name, String email, String phone, String location,
                  String summary, List<String> skills, List<Education> education,
                  List<Experience> experience, List<Certification> certifications, List<Project> projects) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.summary = summary;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.certifications = certifications;
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}

