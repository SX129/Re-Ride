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
    public static final String EXCHANGE = "subscription";
    public static final String ROUTING_KEY = "subscription";
    public static final String QUEUE = "subscriptionQueue";

    @Bean
    public Queue queue(){
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
