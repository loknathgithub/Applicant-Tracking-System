package com.ats.applicantTrackingSystem.Repository;

import com.ats.applicantTrackingSystem.Models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
