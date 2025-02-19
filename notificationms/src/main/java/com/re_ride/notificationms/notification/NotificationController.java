package com.re_ride.notificationms.notification;

import com.re_ride.notificationms.notification.client.UserClient;
import com.re_ride.notificationms.notification.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/notifications")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;

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
    public Notification createNotification(@RequestBody Notification notification){
        Notification savedNotification = notificationService.createNotification(notification);

        if(savedNotification == null){
            System.out.println("User not found.");
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
