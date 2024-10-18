package com.movie_project.movie_Base.Payments;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final UserRepository userRepository;
    private RestTemplate restTemplate;
    private WebClient webClient;

    public static final Integer STATUS_CODE_OK = 200;
    public static final Integer STATUS_CODE_CREATED = 201;
//    public static final String PAYSTACK_INIT = "https://api.paystack.co/plan";
    public static final String PAYSTACK_INITIALIZE_PAY = "https://api.paystack.co/transaction/initialize";
    public static final String PAYSTACK_VERIFY = "https://api.paystack.co/transaction/verify/";

    ObjectMapper mapper = new ObjectMapper();

    @Value("sk_test_c62e04bdb7cbc3998eb3f2b4b073fe43d19b79dd")
    private String secretKey;


    @Override
    public PaymentResponse initializeTransaction(PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = null;
        try{

            Gson gson = new Gson();
            StringEntity request = new StringEntity(gson.toJson(paymentRequest), "application/json");


            HttpHeaders headers =new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization",secretKey);

            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(PAYSTACK_INITIALIZE_PAY);
            httpPost.setEntity(request);
            httpPost.addHeader("Content-type", "application/json");
            httpPost.addHeader("Authorization","Bearer "+ secretKey);
            HttpResponse response = client.execute(httpPost);

            StringBuilder result = new StringBuilder();
            if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK ) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
            }
            } else {
                throw new Exception("Unable to initialize transaction");
            }


            System.out.println(result);
            paymentResponse = mapper.readValue(result.toString(), PaymentResponse.class);

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return paymentResponse;

    }

    @Override
    public VerifyTransactionResponse verifyTransaction(String reference,Long id,String plan) throws Exception {
        VerifyTransactionResponse transactionVerificationResponse= null;
        PaymentPaystack paymentPaystack = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(PAYSTACK_VERIFY + reference);
            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + secretKey);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == STATUS_CODE_CREATED) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new Exception("Error while connecting to paystack url");
            }
            transactionVerificationResponse = mapper.readValue(result.toString(), VerifyTransactionResponse.class);


            if (transactionVerificationResponse == null) {
                throw new Exception("An error occurred while  verifying payment");
            } else
                if (transactionVerificationResponse.getData().getStatus().equals("success")) {

                    User user = userRepository.getById(id);
                    PricingPlanType pricingPlanType = PricingPlanType.valueOf(plan.toUpperCase());


                    PaymentPaystack.builder()
                            .user(user)
                            .reference(transactionVerificationResponse.getData().getReference())
                            .amount(transactionVerificationResponse.getData().getAmount())
                            .gatewayResponse(transactionVerificationResponse.getData().getGatewayResponse())
                            .paidAt(transactionVerificationResponse.getData().getPaidAt())
                            .createdAt(transactionVerificationResponse.getData().getCreatedAt())
                            .channel(transactionVerificationResponse.getData().getChannel())
                            .currency(transactionVerificationResponse.getData().getCurrency())
                            .ipAddress(transactionVerificationResponse.getData().getIpAddress())
                            .pricingPlanType(pricingPlanType)
                            .createdOn(new Date())
                            .build();


                }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Internal server error");
        }
        return transactionVerificationResponse;


        }


    }
