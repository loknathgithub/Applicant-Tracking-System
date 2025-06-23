package com.ats.applicantTrackingSystem.Models.ResumeTemplate;

import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
public class Project {
    private String title;
    private List<String> technologies;
    private String description;

    public Project() {
    }

    public Project(String title, List<String> technologies, String description) {
        this.title = title;
        this.technologies = technologies;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
