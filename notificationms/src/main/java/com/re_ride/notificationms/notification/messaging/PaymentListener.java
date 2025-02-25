package com.re_ride.notificationms.notification.messaging;

import com.re_ride.notificationms.notification.Notification;
import com.re_ride.notificationms.notification.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentListener {
    private NotificationService notificationService;

    public PaymentListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_QUEUE)
    public void handlePaymentCreated(PaymentEvent event){
        System.out.println("Received event: " + event);

        Notification notification = new Notification();

        if(event.getPaymentStatus() == PaymentEvent.PaymentStatus.FAILED){
            notification.setMessage("Your payment could not be processed. Please try again or contact support.");
        }else{
            notification.setMessage("Your payment was successful. Thank you for subscribing!");
        }

        notification.setCreatedAt(LocalDateTime.now());
        notification.setNotificationType(Notification.NotificationType.PAYMENT_RECEIPT);

        notificationService.createNotification(event.getUserId(), notification);
    }
}
