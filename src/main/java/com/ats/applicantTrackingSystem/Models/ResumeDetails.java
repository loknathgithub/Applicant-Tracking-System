package com.ats.applicantTrackingSystem.Models;

import jakarta.persistence.*;
import java.util.*;

@Entity(name = "resume_details")
public class ResumeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Personal Information as a Map
    @ElementCollection
    @CollectionTable(name = "resume_personal_info", joinColumns = @JoinColumn(name = "resume_id"))
    @MapKeyColumn(name = "info_key")
    @Column(name = "info_value")
    private Map<String, String> personalInfo = new HashMap<>();

    // Skills as a Set
    @ElementCollection
    @CollectionTable(name = "resume_skills", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();

    // Skills Count as a Map
    @ElementCollection
    @CollectionTable(name = "resume_skill_occurrences", joinColumns = @JoinColumn(name = "resume_id"))
    @MapKeyColumn(name = "skill")
    @Column(name = "occurrence")
    private Map<String, String> skillsCount = new HashMap<>();

    // Experience
    @ElementCollection
    @CollectionTable(name = "resume_experience", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "experience")
    private List<String> experience = new ArrayList<>();

    // Projects
    @ElementCollection
    @CollectionTable(name = "resume_projects", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "project")
    private List<String> projects = new ArrayList<>();

    // Achievements
    @ElementCollection
    @CollectionTable(name = "resume_achievements", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "achievement")
    private List<String> achievements = new ArrayList<>();

    // Certifications
    @ElementCollection
    @CollectionTable(name = "resume_certifications", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "certification")
    private List<String> certifications = new ArrayList<>();


    public ResumeDetails() {
    }

    public ResumeDetails(
            Map<String, String> personalInfo,
            Set<String> skills,
            Map<String, String> skillsCount,
            List<String> experience,
            List<String> projects,
            List<String> achievements,
            List<String> certifications
    ) {
        this.personalInfo = personalInfo;
        this.skills = skills;
        this.skillsCount = skillsCount;
        this.experience = experience;
        this.projects = projects;
        this.achievements = achievements;
        this.certifications = certifications;
    }

    // --- Getters & Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Map<String, String> getPersonalInfo() { return personalInfo; }
    public void setPersonalInfo(Map<String, String> personalInfo) { this.personalInfo = personalInfo; }

    public Set<String> getSkills() { return skills; }
    public void setSkills(Set<String> skills) { this.skills = skills; }

    public Map<String, String> getSkillsCount() { return skillsCount; }
    public void setSkillsCount(Map<String, String> skillsCount) { this.skillsCount = skillsCount; }

    public List<String> getExperience() { return experience; }
    public void setExperience(List<String> experience) { this.experience = experience; }

    public List<String> getProjects() { return projects; }
    public void setProjects(List<String> projects) { this.projects = projects; }

    public List<String> getAchievements() { return achievements; }
    public void setAchievements(List<String> achievements) { this.achievements = achievements; }

    public List<String> getCertifications() { return certifications; }
    public void setCertifications(List<String> certifications) { this.certifications = certifications; }
}
