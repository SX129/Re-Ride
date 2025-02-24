package com.re_ride.notificationms.notification;

import com.re_ride.notificationms.notification.response.NotificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService, NotificationService notificationService1) {
        this.notificationService = notificationService1;
    }

    @GetMapping()
    public ResponseEntity<List<Notification>> getAllNotificationsByUserId(@PathVariable Long userId, @RequestParam(required = false) String notificationType){
        if(notificationType != null){
            List<Notification> notifications = notificationService.getAllNotificationTypeByUserId(userId, notificationType);

            if(notifications == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(notifications, HttpStatus.OK);
        }else{
            List<Notification> notifications = notificationService.getAllNotificationsByUserId(userId);

            if(notifications == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(notifications, HttpStatus.OK);
        }
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long userId, @PathVariable Long notificationId){
        Notification notification = notificationService.getNotificationById(userId, notificationId);

        if(notification == null){
            return new ResponseEntity<>(new NotificationResponse(null, "User or notification not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new NotificationResponse(notification, "Notification found successfully."), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<NotificationResponse> createNotification(@PathVariable Long userId, @RequestBody Notification notification){
        Notification savedNotification = notificationService.createNotification(userId, notification);

        if(savedNotification == null){
            return new ResponseEntity<>(new NotificationResponse(null, "User or notification not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new NotificationResponse(savedNotification, "Notification created successfully."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long userId, @PathVariable Long notificationId){
        if(notificationService.deleteNotification(userId, notificationId)){
            return new ResponseEntity<>("Notification deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("User or notification not found.", HttpStatus.NOT_FOUND);
    }
}
