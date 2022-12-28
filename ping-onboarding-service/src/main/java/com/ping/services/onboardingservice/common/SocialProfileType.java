package com.ping.services.onboardingservice.common;
import jakarta.persistence.*;

@Embeddable
public class SocialProfileType {
    @Basic
    private String type;
}
