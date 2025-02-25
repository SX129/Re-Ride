package com.re_ride.subscriptionms.subscription.impl;

import com.re_ride.subscriptionms.subscription.Subscription;
import com.re_ride.subscriptionms.subscription.SubscriptionRepository;
import com.re_ride.subscriptionms.subscription.SubscriptionService;
import com.re_ride.subscriptionms.subscription.client.PaymentClient;
import com.re_ride.subscriptionms.subscription.client.UserClient;
import com.re_ride.subscriptionms.subscription.dto.Payment;
import com.re_ride.subscriptionms.subscription.dto.UserDTO;
import com.re_ride.subscriptionms.subscription.messaging.RabbitMQConfig;
import com.re_ride.subscriptionms.subscription.messaging.SubscriptionEvent;
import com.re_ride.subscriptionms.subscription.response.PaymentResponse;
import com.re_ride.subscriptionms.subscription.response.UserResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionRepository subscriptionRepository;
    private UserClient userClient;
    private PaymentClient paymentClient;
    private RabbitTemplate rabbitTemplate;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserClient userClient, PaymentClient paymentClient, RabbitTemplate rabbitTemplate) {
        this.subscriptionRepository = subscriptionRepository;
        this.userClient = userClient;
        this.paymentClient = paymentClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserDTO getUser(Long userId){
        UserResponse response = userClient.getUserById(userId);

        if(response == null || response.getUserDTO() == null || response.getUserDTO().getUserType().equals("DRIVER")) {
            return null;
        }

        return response.getUserDTO();
    }

    public Payment getMostRecentPayment(Long userId){
        PaymentResponse response = paymentClient.getMostRecentPayment(userId);

        if(response == null || response.getPayment() == null) {
            return null;
        }

        return response.getPayment();
    }

    @Override
    public Subscription getSubscriptionByUserId(Long userId) {
        if(getUser(userId) == null){
            return null;
        }

        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public Subscription createSubscription(Long userId, Subscription subscription) {
        if(getUser(userId) == null){
            System.out.println("Invalid user.");
            return null;
        }

        Payment payment = getMostRecentPayment(userId);

        // Invalid payment
        if(payment == null || !payment.getPaymentStatus().equals(Payment.PaymentStatus.COMPLETED)){
            System.out.println("Invalid payment.");
            return null;
        }

        subscription.setUserId(userId);
        subscription.setPaymentId(payment.getPaymentId());
        subscription.setSubscriptionStatus(Subscription.SubscriptionStatus.valueOf("ACTIVE"));
        subscriptionRepository.save(subscription);

        SubscriptionEvent.SubscriptionStatus subscriptionStatus = SubscriptionEvent.SubscriptionStatus.valueOf(subscription.getSubscriptionStatus().name());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.SUBSCRIPTION_EXCHANGE,
                RabbitMQConfig.SUBSCRIPTION_ROUTING_KEY,
                new SubscriptionEvent(subscription.getSubscriptionId(), userId, subscriptionStatus)
        );

        return subscription;
    }

    @Override
    public Subscription updateSubscription(Long userId, Subscription subscription) {
        if(getUser(userId) == null){
            return null;
        }

        Subscription updatedSubscription = subscriptionRepository.findByUserId(userId);

        if(updatedSubscription != null){

            if(subscription.getPaymentId() != null){
                updatedSubscription.setPaymentId(subscription.getPaymentId());
            }

            if(subscription.getSubscriptionAmount() != null){
                updatedSubscription.setSubscriptionAmount(subscription.getSubscriptionAmount());
            }

            if(subscription.getSubscriptionAmount() != null){
                updatedSubscription.setSubscriptionAmount(subscription.getSubscriptionAmount());
            }

            if(subscription.getPickUpDate() != null){
                updatedSubscription.setPickUpDate(subscription.getPickUpDate());
            }

            if(subscription.getPickUpTime() != null){
                updatedSubscription.setPickUpTime(subscription.getPickUpTime());
            }

            if(subscription.getSubscriptionStatus() != null){
                updatedSubscription.setSubscriptionStatus(subscription.getSubscriptionStatus());
            }

            Payment payment = getMostRecentPayment(userId);

            // Invalid payment
            if(payment == null || !payment.getPaymentStatus().equals("COMPLETED")){
                updatedSubscription.setSubscriptionStatus(Subscription.SubscriptionStatus.valueOf("PAUSED"));
            }else{
                updatedSubscription.setSubscriptionStatus(Subscription.SubscriptionStatus.valueOf("ACTIVE"));
            }

            updatedSubscription.setUpdatedAt(LocalDateTime.now());

            subscriptionRepository.save(updatedSubscription);
            return updatedSubscription;
        }

        return null;
    }

    @Override
    public boolean deleteSubscription(Long userId) {
        if(getUser(userId) == null){
            return false;
        }

        Subscription subscription = subscriptionRepository.findByUserId(userId);

        if(subscription != null){
            subscriptionRepository.deleteById(subscription.getSubscriptionId());
            return true;
        }

        return false;
    }
}
