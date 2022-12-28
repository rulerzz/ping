package com.ping.services.onboardingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ping.services.onboardingservice.common.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "personal-info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String initials;
    private LocalDate dob;
    private Gender gender;

    @OneToOne(mappedBy = "personalInfoId")
    private Address address;

    private String companyName;
    private String title;

    @OneToOne(mappedBy = "personalInfo")
    @JsonIgnore
    private Account account;
    public PersonalInfo(String firstName, String lastName, String middleName, String dob, Account account) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.account = account;
    }
}
