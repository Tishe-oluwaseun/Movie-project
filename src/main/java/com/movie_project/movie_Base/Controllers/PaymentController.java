package com.movie_project.movie_Base.Controllers;


import com.movie_project.movie_Base.Payments.PaymentRequest;
import com.movie_project.movie_Base.Payments.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/pay")
    public ResponseEntity paySubscription(@RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.ok(paymentService.initializeTransaction(paymentRequest));
    }

    @PostMapping("/verify/{reference}")
    public ResponseEntity VerifySubscription(@PathVariable String reference,String Plan,Long  UserId) throws Exception {

        return ResponseEntity.ok(paymentService.verifyTransaction(reference,UserId,Plan));
    }


}
