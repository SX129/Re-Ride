package com.re_ride.subscriptionms.subscription;

import com.re_ride.subscriptionms.subscription.dto.SubscriptionDTO;
import com.re_ride.subscriptionms.subscription.mapper.SubscriptionMapper;
import com.re_ride.subscriptionms.subscription.response.SubscriptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/subscription")
public class SubscriptionController {
    private SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    //get subscription of user
    @GetMapping()
    public ResponseEntity<SubscriptionResponse> getSubscriptionByUserId(@PathVariable Long userId){
        Subscription subscription = subscriptionService.getSubscriptionByUserId(userId);

        if(subscription == null){
            return new ResponseEntity<>(new SubscriptionResponse(null, "Subscription not found."), HttpStatus.NOT_FOUND);
        }

        SubscriptionDTO subscriptionDTO = SubscriptionMapper.mapSubscriptionDto(subscription);

        return new ResponseEntity<>(new SubscriptionResponse(subscriptionDTO, "Subscription found successfully."), HttpStatus.OK);
    }

    //create subscription
    @PostMapping()
    public ResponseEntity<SubscriptionResponse> createSubscription(@PathVariable Long userId, @RequestBody Subscription subscription){
        Subscription savedSubscription = subscriptionService.createSubscription(userId, subscription);

        if(savedSubscription == null){
            return new ResponseEntity<>(new SubscriptionResponse(null, "Unable to create subscription."), HttpStatus.NOT_FOUND);
        }

        SubscriptionDTO subscriptionDTO = SubscriptionMapper.mapSubscriptionDto(savedSubscription);

        return new ResponseEntity<>(new SubscriptionResponse(subscriptionDTO, "Subscription created successfully."), HttpStatus.CREATED);
    }

    //update subscription
    @PatchMapping()
    public ResponseEntity<SubscriptionResponse> updateSubscription(@PathVariable Long userId, @RequestBody Subscription subscription){
        Subscription updatedSubscription = subscriptionService.updateSubscription(userId, subscription);

        if(updatedSubscription == null){
            return new ResponseEntity<>(new SubscriptionResponse(null, "Subscription not found."), HttpStatus.NOT_FOUND);
        }

        SubscriptionDTO subscriptionDTO = SubscriptionMapper.mapSubscriptionDto(updatedSubscription);

        return new ResponseEntity<>(new SubscriptionResponse(subscriptionDTO, "Subscription updated successfully."), HttpStatus.OK);
    }

    //delete subscription
    @DeleteMapping
    public ResponseEntity<String> deleteSubscription(@PathVariable Long userId){
        if(subscriptionService.deleteSubscription(userId) == false){
            return new ResponseEntity<>("Subscription not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Subscription deleted successfully.", HttpStatus.OK);
    }
}
