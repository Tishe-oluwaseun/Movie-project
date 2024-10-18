package com.movie_project.movie_Base.DTOs;

import com.movie_project.movie_Base.Payments.PricingPlanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private PricingPlanType pricingPlan;

}
