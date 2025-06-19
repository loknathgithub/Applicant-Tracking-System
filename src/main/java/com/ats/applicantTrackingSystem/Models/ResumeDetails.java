package com.ats.applicantTrackingSystem.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Entity(name = "resume_details")
public class ResumeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Id;

    @NotNull
    @Column(name = "personal information")
    private HashMap<String, String> personalInfo;

    @NotNull
    private HashSet<String> skills;

    @NotNull
    @Column(name = "skill occurrances")
    private HashMap<String, String> skillsCount;

    private List<String> experience = new ArrayList<>();
    private List<String> projects = new ArrayList<>();
    private List<String> achievements = new ArrayList<>();
    private List<String> certifications = new ArrayList<>();

    public ResumeDetails() {
    }

    public ResumeDetails(String id, HashMap<String, String> personalInfo, HashSet<String> skills,
                         HashMap<String, String> skillsCount, List<String> experience, List<String> projects,
                         List<String> achievements, List<String> certifications) {
        Id = id;
        this.personalInfo = personalInfo;
        this.skills = skills;
        this.skillsCount = skillsCount;
        this.experience = experience;
        this.projects = projects;
        this.achievements = achievements;
        this.certifications = certifications;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public HashMap<String, String> getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(HashMap<String, String> personalInfo) {
        this.personalInfo = personalInfo;
    }

    public HashSet<String> getSkills() {
        return skills;
    }

    public void setSkills(HashSet<String> skills) {
        this.skills = skills;
    }

    public HashMap<String, String> getSkillsCount() {
        return skillsCount;
    }

    public void setSkillsCount(HashMap<String, String> skillsCount) {
        this.skillsCount = skillsCount;
    }

    public List<String> getExperience() {
        return experience;
    }

    public void setExperience(List<String> experience) {
        this.experience = experience;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }
}
