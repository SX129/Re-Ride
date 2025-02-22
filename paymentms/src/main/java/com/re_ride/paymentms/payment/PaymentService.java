package com.re_ride.paymentms.payment;

import java.util.List;

public interface PaymentService {

    Payment getMostRecentPayment(Long userId);
    List<Payment> getAllPaymentsByUserId(Long userId);

    List<Payment> getAllPaymentStatusByUserId(Long userId, String paymentStatus);

    Payment getPaymentById(Long userId, Long paymentId);
    Payment createPayment(Long userId, Payment payment);
    Payment updatePaymentStatus(Long userId, Long paymentId, String paymentStatus);
    boolean deletePayment(Long userId, Long paymentId);
}
