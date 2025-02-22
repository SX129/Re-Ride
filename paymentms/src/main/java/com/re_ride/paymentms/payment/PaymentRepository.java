package com.re_ride.paymentms.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);

    @Query(value = "SELECT * FROM payments p WHERE p.user_id = :userId ORDER BY p.created_at DESC LIMIT 1", nativeQuery = true)
    Payment findTopByUserIdOrderByCreatedAtDesc(Long userId);
}
