package com.ats.applicantTrackingSystem.Services.Implementations;

import com.ats.applicantTrackingSystem.Models.Notification;
import com.ats.applicantTrackingSystem.Repository.NotificationRepository;
import com.ats.applicantTrackingSystem.Services.Interfaces.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService implements NotificationServiceImpl {
    @Autowired
    private NotificationRepository repo;

    public void notifyUser(Long userId, String message) {
        repo.save(new Notification(null, userId, message, false, LocalDateTime.now()));
    }

    public List<Notification> getUserNotifications(Long userId) {
        return repo.findByRecipientUserId(userId);
    }

    public void markAsRead(Long notificationId) {
        Notification n = repo.findById(notificationId).orElseThrow();
        n.setRead(true);
        repo.save(n);
    }
}