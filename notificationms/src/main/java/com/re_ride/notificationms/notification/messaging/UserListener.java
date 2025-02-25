package com.re_ride.notificationms.notification.messaging;

import com.re_ride.notificationms.notification.Notification;
import com.re_ride.notificationms.notification.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserListener {
    private NotificationService notificationService;

    public UserListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleUserCreated(UserEvent event){
        System.out.println("Received event: " + event);

        Notification notification = new Notification();

        if(event.getUserType() == UserEvent.UserType.RIDER){
            notification.setMessage("Welcome to Re-Ride! Thanks for riding with us.");
        }else{
            notification.setMessage("Welcome to Re-Ride! Thanks for driving with us.");
        }

        notification.setCreatedAt(LocalDateTime.now());
        notification.setNotificationType(Notification.NotificationType.WELCOME);

        notificationService.createNotification(event.getUserId(), notification);
    }
}
