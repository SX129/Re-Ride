package com.re_ride.notificationms.notification.client;

import com.re_ride.notificationms.notification.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userms", url = "http://localhost:8081/users")
public interface UserClient {

    @GetMapping("/{userId}")
    UserDTO getUserById(@PathVariable Long userId);
}
