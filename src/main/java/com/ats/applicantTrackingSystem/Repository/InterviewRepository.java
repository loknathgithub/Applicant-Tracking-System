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
    List<Interview> findByJob_Id_JobIdAndJob_Id_CompanyNameAndJob_Id_RecruiterId(String jobId, String companyName, Long recruiterId);

    // Find all interviews for a candidate/application
    List<Interview> findByApplication_Id(Long applicationId);

    List<Interview> findByApplication_Resume_UserId(Long userId);
}
