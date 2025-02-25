package com.re_ride.paymentms.payment.impl;

import com.re_ride.paymentms.payment.Payment;
import com.re_ride.paymentms.payment.PaymentRepository;
import com.re_ride.paymentms.payment.PaymentService;
import com.re_ride.paymentms.payment.client.SubscriptionClient;
import com.re_ride.paymentms.payment.client.UserClient;
import com.re_ride.paymentms.payment.dto.SubscriptionDTO;
import com.re_ride.paymentms.payment.dto.UserDTO;
import com.re_ride.paymentms.payment.messaging.PaymentEvent;
import com.re_ride.paymentms.payment.messaging.RabbitMQConfig;
import com.re_ride.paymentms.payment.response.SubscriptionResponse;
import com.re_ride.paymentms.payment.response.UserResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private UserClient userClient;
    private SubscriptionClient subscriptionClient;
    private RabbitTemplate rabbitTemplate;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserClient userClient, SubscriptionClient subscriptionClient, RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.userClient = userClient;
        this.subscriptionClient = subscriptionClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserDTO getUser(Long userId){
        UserResponse response = userClient.getUserById(userId);

        if(response == null || response.getUserDTO() == null || response.getUserDTO().getUserType().equals("DRIVER")) {
            return null;
        }

        return response.getUserDTO();
    }

    public SubscriptionDTO getSubscription(Long userId) {
        try {
            SubscriptionResponse response = subscriptionClient.getSubscriptionByUserId(userId);

            if (response == null || response.getSubscriptionDTO() == null) {
                return null;
            }

            return response.getSubscriptionDTO();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Payment getMostRecentPayment(Long userId) {
        if(getUser(userId) == null){
            return null;
        }

        return paymentRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public List<Payment> getAllPaymentsByUserId(Long userId) {
        if(getUser(userId) == null){
            return null;
        }

        return paymentRepository.findByUserId(userId);
    }

    @Override
    public List<Payment> getAllPaymentStatusByUserId(Long userId, String paymentStatus) {
        if(getUser(userId) == null){
            return null;
        }

        Payment.PaymentStatus status = Payment.PaymentStatus.valueOf(paymentStatus);

        return paymentRepository.findByUserId(userId).stream()
                .filter(payment -> payment.getPaymentStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Payment getPaymentById(Long userId, Long paymentId) {
        if(getUser(userId) == null){
            return null;
        }

        Payment payment = paymentRepository.findById(paymentId).orElse(null);

        if(payment != null && payment.getUserId().equals(userId)){
            return payment;
        }

        return null;
    }

    @Override
    public Payment createPayment(Long userId, Payment payment) {
        if(getUser(userId) == null){
            return null;
        }

        payment.setUserId(userId);
        PaymentEvent.PaymentStatus paymentStatus = PaymentEvent.PaymentStatus.valueOf(payment.getPaymentStatus().name());

        SubscriptionDTO subscriptionDTO = getSubscription(userId);

        if(subscriptionDTO != null){
            payment.setTotalAmount(subscriptionDTO.getSubscriptionAmount());
            payment.setSubscriptionId(subscriptionDTO.getSubscriptionId());

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY,
                    new PaymentEvent(payment.getPaymentId(), userId, subscriptionDTO.getSubscriptionId(), subscriptionDTO.getSubscriptionAmount(), paymentStatus)
            );
        }else{
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY,
                    new PaymentEvent(payment.getPaymentId(), userId, null, payment.getTotalAmount(), paymentStatus)
            );
        }

        paymentRepository.save(payment);

        return payment;
    }

    @Override
    public Payment updatePaymentStatus(Long userId, Long paymentId, String paymentStatus) {
        if(getUser(userId) == null){
            return null;
        }

        Payment payment = paymentRepository.findById(paymentId).orElse(null);

        if(payment != null && payment.getUserId().equals(userId)){
            Payment.PaymentStatus status = Payment.PaymentStatus.valueOf(paymentStatus);
            payment.setPaymentStatus(status);

            paymentRepository.save(payment);

            return payment;
        }

        return null;
    }

    @Override
    public boolean deletePayment(Long userId, Long paymentId) {
        if(getUser(userId) == null){
            return false;
        }

        Payment payment = paymentRepository.findById(paymentId).orElse(null);

        if(payment != null && payment.getUserId().equals(userId)){
            paymentRepository.deleteById(paymentId);
            return true;
        }

        return false;
    }
}
