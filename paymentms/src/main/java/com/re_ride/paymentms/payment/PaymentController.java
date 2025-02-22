package com.re_ride.paymentms.payment;

import com.re_ride.paymentms.payment.response.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    //get most recent payment of user
    @GetMapping("/recent")
    public ResponseEntity<PaymentResponse> getMostRecentPayment(@PathVariable Long userId){
        Payment payment = paymentService.getMostRecentPayment(userId);

        if(payment == null){
            return new ResponseEntity<>(new PaymentResponse(null, "User or payment not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PaymentResponse(payment, "Payment found."), HttpStatus.OK);
    }

    //get all payments of user
    @GetMapping()
    public ResponseEntity<List<Payment>> getAllPaymentsByUserId(@PathVariable Long userId, @RequestParam(required = false) String paymentStatus){
        if(paymentStatus != null){
            List<Payment> payments = paymentService.getAllPaymentStatusByUserId(userId, paymentStatus);

            if(payments == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(payments, HttpStatus.OK);
        }else{
            List<Payment> payments = paymentService.getAllPaymentsByUserId(userId);

            if(payments == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(payments, HttpStatus.OK);
        }
    }

    //get a payment of a user
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long userId, @PathVariable Long paymentId){
        Payment payment = paymentService.getPaymentById(userId, paymentId);

        if(payment == null){
            return new ResponseEntity<>(new PaymentResponse(null, "User or payment not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PaymentResponse(payment, "Payment found."), HttpStatus.OK);
    }

    //create a payment
    @PostMapping()
    public ResponseEntity<PaymentResponse> createPayment(@PathVariable Long userId, @RequestBody Payment payment){
        Payment savedPayment = paymentService.createPayment(userId, payment);

        if(savedPayment == null){
            return new ResponseEntity<>(new PaymentResponse(null, "Rider not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PaymentResponse(payment, "Payment created successfully."), HttpStatus.CREATED);
    }

    //update status of payment
    @PatchMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> updatePaymentStatus(@PathVariable Long userId, @PathVariable Long paymentId, @RequestBody String paymentStatus){
        paymentStatus = paymentStatus.replace("\"", "");
        Payment updatedPayment = paymentService.updatePaymentStatus(userId, paymentId, paymentStatus);

        if(updatedPayment == null){
            return new ResponseEntity<>(new PaymentResponse(null, "User or payment not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PaymentResponse(updatedPayment, "Payment created successfully."), HttpStatus.OK);
    }

    //delete a payment
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long userId, @PathVariable Long paymentId){
        if(paymentService.deletePayment(userId, paymentId)){
            return new ResponseEntity<>("Payment deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("User or payment not found.", HttpStatus.NOT_FOUND);
    }
}
