package com.ping.services.onboardingservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserNameDTO {
    public String phoneNumber;
    public String email;
    @NotEmpty
    @NotNull
    public String userName;
}
