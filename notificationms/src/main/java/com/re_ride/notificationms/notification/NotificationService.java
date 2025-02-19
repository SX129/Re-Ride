package com.re_ride.notificationms.notification;

import com.re_ride.notificationms.notification.dto.UserDTO;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotificationsByUserId(Long userId);

    List<Notification> getAllNotificationTypeByUserId(Long userId, String notificationType);

    Notification createNotification(Notification notification);

    boolean deleteNotification(Long userId, Long notificationId);

    UserDTO getUser(Long userId);
}
