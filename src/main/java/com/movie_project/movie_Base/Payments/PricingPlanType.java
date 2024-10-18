package com.movie_project.movie_Base.Payments;

import lombok.*;

@Getter
public enum PricingPlanType {
    BASIC("Basic"),
    PREMIUM("Premium");

    private final String value;

    PricingPlanType(String value) {
        this.value = value;
    }


}
