package com.ats.applicantTrackingSystem.Services.Interfaces;

import com.ats.applicantTrackingSystem.Models.Notification;
import java.util.List;

public interface NotificationServiceImpl {
    public void notifyUser(Long userId, String message);
    public List<Notification> getUserNotifications(Long userId);
    public void markAsRead(Long notificationId);
}

