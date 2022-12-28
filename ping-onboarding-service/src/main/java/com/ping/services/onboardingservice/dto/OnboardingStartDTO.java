package com.ping.services.onboardingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OnboardingStartDTO {
    @NotEmpty
    @NotNull
    private String firstName;

    private String middleName;

    @NotEmpty
    @NotNull
    private String lastName;

    private String dialCode;
    private String phoneNumber;

    @NotEmpty
    @NotNull
    private String defaultCountry;


    @Email
    private String email;

    @NotEmpty
    @NotNull
    private String dob;
}
