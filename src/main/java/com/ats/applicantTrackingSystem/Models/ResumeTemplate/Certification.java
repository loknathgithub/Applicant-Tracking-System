package com.ats.applicantTrackingSystem.Models.ResumeTemplate;

import jakarta.persistence.Embeddable;

@Embeddable
public class Certification {
    private String name;
    private String issuer;
    private String issueDate;

    public Certification() {
    }

    public Certification(String name, String issuer, String issueDate) {
        this.name = name;
        this.issuer = issuer;
        this.issueDate = issueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}
