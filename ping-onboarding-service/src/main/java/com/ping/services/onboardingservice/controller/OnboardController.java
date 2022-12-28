package com.ping.services.onboardingservice.controller;

import com.ping.services.onboardingservice.dto.OnboardingResponseDTO;
import com.ping.services.onboardingservice.dto.OnboardingStartDTO;
import com.ping.services.onboardingservice.dto.UpdateUserNameDTO;
import com.ping.services.onboardingservice.exceptions.OnboardingException;
import com.ping.services.onboardingservice.model.Account;
import com.ping.services.onboardingservice.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/onboard")
public class OnboardController {

    @Autowired
    private OnboardingService onboardingService;

    @GetMapping
    public ResponseEntity<List<Account>> listAccounts() {
        return new ResponseEntity<>(this.onboardingService.listAllAccounts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OnboardingResponseDTO> onboardStart(@RequestBody OnboardingStartDTO onboardingRequest) {
        OnboardingResponseDTO response = this.onboardingService.startOnboarding(onboardingRequest);
       return new ResponseEntity<>(response, HttpStatus.resolve(response.getStatusCode()));
    }

    @PutMapping
    public ResponseEntity<OnboardingResponseDTO> updateUsername(@RequestBody UpdateUserNameDTO updateRequest) {
        OnboardingResponseDTO response = this.onboardingService.updateUserName(updateRequest);
        return new ResponseEntity<>(response, HttpStatus.resolve(response.getStatusCode()));
    }
}
