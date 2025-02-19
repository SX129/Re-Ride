package com.re_ride.notificationms.notification.response;

import com.re_ride.notificationms.notification.Notification;

public class NotificationResponse {
    private Notification notification;
    private String message;

    public NotificationResponse(Notification notification, String message) {
        this.notification = notification;
        this.message = message;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
