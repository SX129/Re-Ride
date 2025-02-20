package com.re_ride.paymentms.payment.impl;

import com.re_ride.paymentms.payment.Payment;
import com.re_ride.paymentms.payment.PaymentRepository;
import com.re_ride.paymentms.payment.PaymentService;
import com.re_ride.paymentms.payment.client.UserClient;
import com.re_ride.paymentms.payment.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private UserClient userClient;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserClient userClient) {
        this.paymentRepository = paymentRepository;
        this.userClient = userClient;
    }

    public UserDTO getUser(Long userId){
        UserDTO user = userClient.getUserById(userId);

        if(user == null || user.getUserType() != "RIDER"){
            return null;
        }

        return user;
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
