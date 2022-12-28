package com.ping.services.onboardingservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String countryCode;
    private String phone;
    private String email;

    @OneToOne(mappedBy = "contact")
    @JsonIgnore
    private Account account;

    public Contact(int countryCode, long nationalNumber, String email, Account account) {
        this.countryCode = Integer.toString(countryCode);
        this.phone = Long.toString(nationalNumber);
        this.email = email;
        this.account = account;
    }
}
