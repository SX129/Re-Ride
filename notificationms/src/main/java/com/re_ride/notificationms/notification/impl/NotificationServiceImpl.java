package com.re_ride.notificationms.notification.impl;

import com.re_ride.notificationms.notification.Notification;
import com.re_ride.notificationms.notification.NotificationRepository;
import com.re_ride.notificationms.notification.NotificationService;
import com.re_ride.notificationms.notification.client.UserClient;
import com.re_ride.notificationms.notification.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private UserClient userClient;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserClient userClient) {
        this.notificationRepository = notificationRepository;
        this.userClient = userClient;
    }

    public UserDTO getUser(Long userId){
        UserDTO user = userClient.getUserById(userId);

        if(user == null){
            return null;
        }

        return user;
    }

    public List<Notification> getAllNotificationsByUserId(Long userId){
        if(getUser(userId) == null){
            return null;
        }

        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getAllNotificationTypeByUserId(Long userId, String notificationType) {
        if (getUser(userId) == null) {
            return null;
        }

        Notification.NotificationType type = Notification.NotificationType.valueOf(notificationType);

        return notificationRepository.findByUserId(userId).stream()
                .filter(notification -> notification.getNotificationType() == type)
                .collect(Collectors.toList());
    }

    public Notification getNotificationById(Long userId, Long notificationId){
        if (getUser(userId) == null) {
            return null;
        }

        Notification notification = notificationRepository.findById(notificationId).orElse(null);

        if (notification != null && notification.getUserId().equals(userId)) {
            return notification;
        }

        return null;
    }

    public Notification createNotification(Long userId, Notification notification) {
        if (getUser(userId) == null) {
            return null;
        }

        notification.setUserId(userId);
        notificationRepository.save(notification);

        return notification;
    }

    public boolean deleteNotification(Long userId, Long notificationId) {
        if (getUser(userId) == null) {
            return false;
        }

        Notification notification = notificationRepository.findById(notificationId).orElse(null);

        if (notification != null && notification.getUserId().equals(userId)) {
            notificationRepository.deleteById(notificationId);
            return true;
        }

        return false;
    }
}
