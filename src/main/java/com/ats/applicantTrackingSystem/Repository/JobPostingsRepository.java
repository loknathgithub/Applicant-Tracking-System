package com.ats.applicantTrackingSystem.Repository;

import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingsRepository extends JpaRepository<JobPostDetails, CompositePrimaryKeyConfig> {
    List<JobPostDetails> findByIdRecruiterId(Long recruiterId);
}
