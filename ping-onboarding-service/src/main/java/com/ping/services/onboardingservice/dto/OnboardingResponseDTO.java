package com.ping.services.onboardingservice.dto;

import com.ping.services.onboardingservice.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnboardingResponseDTO {
    public String status;
    public String message;
    public Account data;
    public Integer statusCode;
}
