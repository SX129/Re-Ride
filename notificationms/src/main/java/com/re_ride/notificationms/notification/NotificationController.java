package com.re_ride.notificationms.notification;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService, NotificationService notificationService1) {
        this.notificationService = notificationService1;
    }

    @GetMapping()
    public List<Notification> getAllNotificationsByUserId(@PathVariable Long userId, @RequestParam(required = false) String notificationType){
        if(notificationType != null){
            List<Notification> notifications = notificationService.getAllNotificationTypeByUserId(userId, notificationType);

            if(notifications == null){
                System.out.println("User or notification type not found.");
                return null;
            }
        }

        List<Notification> notifications = notificationService.getAllNotificationsByUserId(userId);

        if(notifications == null){
            System.out.println("User not found.");
            return null;
        }

        return notifications;
    }

    @PostMapping()
    public Notification createNotification(@PathVariable Long userId, @RequestBody Notification notification){
        Notification savedNotification = notificationService.createNotification(userId, notification);

        if(savedNotification == null){
            System.out.println("User or notification type not found.");
            return null;
        }

        return savedNotification;
    }

    @DeleteMapping("/{notificationId}")
    public boolean deleteNotification(@PathVariable Long userId, @PathVariable Long notificationId){
        if(notificationService.deleteNotification(userId, notificationId)){
            return true;
        }

        System.out.println("User or notification not found.");
        return false;
    }
}
