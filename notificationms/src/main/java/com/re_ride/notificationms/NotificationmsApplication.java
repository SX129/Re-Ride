package com.re_ride.notificationms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificationmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationmsApplication.class, args);
	}

}
