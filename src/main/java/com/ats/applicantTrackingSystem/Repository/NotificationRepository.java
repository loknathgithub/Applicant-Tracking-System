package com.ats.applicantTrackingSystem.Repository;

import com.ats.applicantTrackingSystem.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientUserId(Long userId);
}

