package com.ats.applicantTrackingSystem.Repository;

import com.ats.applicantTrackingSystem.Models.ResumeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeDetailsRepository extends JpaRepository<ResumeDetails, Long> {
}
