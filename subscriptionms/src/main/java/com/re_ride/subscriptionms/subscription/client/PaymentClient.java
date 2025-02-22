package com.re_ride.subscriptionms.subscription.client;

import com.re_ride.subscriptionms.subscription.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "paymentms", url = "http://localhost:8083/users")
public interface PaymentClient {

    @GetMapping("/{userId}/payments/recent")
    PaymentResponse getMostRecentPayment(@PathVariable Long userId);
}
