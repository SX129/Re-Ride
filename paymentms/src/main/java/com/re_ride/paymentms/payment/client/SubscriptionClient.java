package com.re_ride.paymentms.payment.client;

import com.re_ride.paymentms.payment.response.SubscriptionResponse;
import com.re_ride.paymentms.payment.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "subscriptionms")
public interface SubscriptionClient {

    @GetMapping("/users/{userId}/subscription")
    SubscriptionResponse getSubscriptionByUserId(@PathVariable Long userId);
}
