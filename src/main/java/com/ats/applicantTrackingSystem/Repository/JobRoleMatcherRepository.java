package com.ats.applicantTrackingSystem.Repository;

import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.JobRoleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRoleMatcherRepository extends JpaRepository<JobRoleMatcher, CompositePrimaryKeyConfig> {
}
