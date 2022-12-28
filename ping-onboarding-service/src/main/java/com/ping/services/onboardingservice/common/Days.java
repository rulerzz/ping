package com.ping.services.onboardingservice.common;

import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;

@Embeddable
public class Days {

    @Basic
    private String day;
}
