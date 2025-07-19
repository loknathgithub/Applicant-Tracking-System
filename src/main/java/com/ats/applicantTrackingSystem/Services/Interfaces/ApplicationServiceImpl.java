package com.ats.applicantTrackingSystem.Services.Interfaces;

import com.ats.applicantTrackingSystem.DTO.ApplicationDTO;
import com.ats.applicantTrackingSystem.Models.Application;
import com.ats.applicantTrackingSystem.Models.ENUM.ApplicationStatus;


public interface ApplicationServiceImpl {
    public Application processApplicationSubmission(ApplicationDTO dto);
    public ApplicationStatus getApplicationStatus(Long applicationId);
    public ApplicationStatus updateApplicationStatus(Long applicationId, ApplicationStatus newStatus);

}
