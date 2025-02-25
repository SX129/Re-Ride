package com.re_ride.subscriptionms.subscription.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String SUBSCRIPTION_EXCHANGE = "subscription";
    public static final String SUBSCRIPTION_ROUTING_KEY = "subscription";
    public static final String SUBSCRIPTION_QUEUE = "subscriptionQueue";

    public static final String RIDE_EXCHANGE = "ride";
    public static final String RIDE_ROUTING_KEY = "ride";
    public static final String RIDE_QUEUE = "rideQueue";

    @Bean
    public Queue subscriptionQueue(){
        return QueueBuilder.durable(SUBSCRIPTION_QUEUE).build();
    }

    @Bean
    public DirectExchange subscriptionExchange(){
        return new DirectExchange(SUBSCRIPTION_EXCHANGE);
    }

    @Bean
    public Binding subscriptionBinding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(SUBSCRIPTION_ROUTING_KEY);
    }

    @Bean
    public Queue rideQueue(){
        return QueueBuilder.durable(RIDE_QUEUE).build();
    }

    @Bean
    public DirectExchange rideExchange(){
        return new DirectExchange(RIDE_EXCHANGE);
    }

    @Bean
    public Binding rideBinding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RIDE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
