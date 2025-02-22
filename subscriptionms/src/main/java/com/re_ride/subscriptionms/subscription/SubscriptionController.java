package com.re_ride.subscriptionms.subscription;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/subscription")
public class SubscriptionController {

    //get subscription of user
    @GetMapping()
    public Subscription getSubscriptionByUserId(@PathVariable Long userId){
        return null;
    }

    //create subscription
    @PostMapping()
    public Subscription createSubscription(@PathVariable Long userId, @RequestBody Subscription subscription){
        return null;
    }

    //update subscription
    @PatchMapping()
    public Subscription updateSubscription(@PathVariable Long userId, @RequestBody Subscription subscription){
        return null;
    }

    //delete subscription
    @DeleteMapping
    public String deleteSubscription(@PathVariable Long userId){
        return "false";
    }
}
