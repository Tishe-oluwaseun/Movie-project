package com.movie_project.movie_Base.Payments;


import org.springframework.http.ResponseEntity;

public interface PaymentService {

    PaymentResponse initializeTransaction(PaymentRequest request);

    VerifyTransactionResponse verifyTransaction(String reference,Long id,String plan) throws Exception;
}
