package com.ats.applicantTrackingSystem.Models.ResumeTemplate;

import jakarta.persistence.Embeddable;

@Embeddable
public class Education {
    private String degree;
    private String field;
    private String institution;
    private String startYear;
    private String endYear;

    public Education() {
    }

    public Education(String degree, String field, String institution, String startYear, String endYear) {
        this.degree = degree;
        this.field = field;
        this.institution = institution;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }
}
