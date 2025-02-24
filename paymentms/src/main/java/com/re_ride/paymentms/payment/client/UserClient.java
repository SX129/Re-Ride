package com.re_ride.paymentms.payment.client;

import com.re_ride.paymentms.payment.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userms")
public interface UserClient {

    @GetMapping("/api/users/{userId}")
    UserResponse getUserById(@PathVariable Long userId);
}
