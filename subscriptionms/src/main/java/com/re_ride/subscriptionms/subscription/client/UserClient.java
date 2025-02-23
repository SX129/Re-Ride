package com.re_ride.subscriptionms.subscription.client;

import com.re_ride.subscriptionms.subscription.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userms")
public interface UserClient {
    @GetMapping("/users/{userId}")
    UserResponse getUserById(@PathVariable Long userId);
}
