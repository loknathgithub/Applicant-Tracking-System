package com.ats.applicantTrackingSystem.Repository;

import com.ats.applicantTrackingSystem.Models.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    // Find all interviews for jobs by recruiter
    List<Interview> findByJob_Id_RecruiterId(Long recruiterId);

    // Find all interviews for a specific job
    List<Interview> findByJob_Id_JobIDAndJob_Id_JobCompanyNameAndJob_Id_RecruiterId(String jobID, String jobCompanyName, Long recruiterId);

    // Find all interviews for a candidate/application
    List<Interview> findByApplication_ApplicationId(Long applicationId);

    List<Interview> findByApplication_Resume_UserId(Long userId);
}
