package com.re_ride.notificationms.notification.messaging;

import com.re_ride.notificationms.notification.Notification;
import com.re_ride.notificationms.notification.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SubscriptionListener {
    private NotificationService notificationService;

    public SubscriptionListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.SUBSCRIPTION_QUEUE)
    public void handleSubscriptionRequests(SubscriptionEvent event){
        System.out.println("Received event: " + event);

        Notification notification = new Notification();

        notification.setMessage("Your subscription is now active. Enjoy our services!");
        notification.setCreatedAt(LocalDateTime.now());
        notification.setNotificationType(Notification.NotificationType.WELCOME);

        notificationService.createNotification(event.getUserId(), notification);
    }
}
