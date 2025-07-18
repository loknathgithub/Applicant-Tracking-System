package com.ats.applicantTrackingSystem.Repository;

import com.ats.applicantTrackingSystem.Models.Application;
import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.ENUM.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(ApplicationStatus status);
    Long countByJobPost_Id(CompositePrimaryKeyConfig jobId);
    List<Application> findByJobPost_Id(CompositePrimaryKeyConfig jobId);
    List<Application> findByJobPost_IdRecruiterId(Long recruiterId);
    List<Application> findByResume_UserId(Long userId);
}
