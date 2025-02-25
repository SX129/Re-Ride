package com.re_ride.notificationms.notification.messaging;

import com.re_ride.notificationms.notification.Notification;
import com.re_ride.notificationms.notification.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RideListener {
    private NotificationService notificationService;

    public RideListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.RIDE_QUEUE)
    public void handleRideRequests(RideEvent event){
        System.out.println("Received event: " + event);

        Notification notification = new Notification();
        notification.setCreatedAt(LocalDateTime.now());
        notification.setNotificationType(Notification.NotificationType.RIDE_UPDATE);

        switch (event.getRideStatus()) {
            case PENDING:
                notification.setMessage("Your ride is in progress.");
                break;
            case COMPLETED:
                notification.setMessage("Your ride has been completed. Thanks for riding!");
                break;
            case CANCELLED:
                notification.setMessage("Your ride has been cancelled.");
                break;
            case MISSED:
                notification.setMessage("You missed your ride.");
                break;
            default:
                throw new IllegalArgumentException("Unknown ride status: " + event.getRideStatus());
        }

        notificationService.createNotification(event.getUserId(), notification);
    }
}
