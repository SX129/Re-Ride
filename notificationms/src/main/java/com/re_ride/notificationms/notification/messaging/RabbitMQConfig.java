package com.re_ride.notificationms.notification.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String USER_QUEUE = "userQueue";
    public static final String PAYMENT_QUEUE = "paymentQueue";
    public static final String SUBSCRIPTION_QUEUE = "subscriptionQueue";
    public static final String RIDE_QUEUE = "rideQueue";

    @Bean
    public Queue userQueue(){
        return QueueBuilder.durable(USER_QUEUE).build();
    }

    @Bean
    public Queue paymentQueue(){
        return QueueBuilder.durable(PAYMENT_QUEUE).build();
    }

    @Bean
    public Queue subscriptionQueue(){
        return QueueBuilder.durable(SUBSCRIPTION_QUEUE).build();
    }

    @Bean
    public Queue rideQueue(){
        return QueueBuilder.durable(RIDE_QUEUE).build();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
