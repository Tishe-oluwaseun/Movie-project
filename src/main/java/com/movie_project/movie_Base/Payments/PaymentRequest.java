package com.movie_project.movie_Base.Payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//Class to initialize payment
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    @NotNull(message = "Amount cannot be null")
    @JsonProperty("amount")
    private String amount;

    @NotNull(message = "Email cannot be null")
    @JsonProperty("email")
    private String email;


    @NotNull(message = "Currency cannot be null")
    @JsonProperty("currency")
    private String currency;

    @NotNull(message = "Plan cannot be null")
    @JsonProperty("plan")
    private String plan;

}
