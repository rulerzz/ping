package com.ping.services.onboardingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ping.services.onboardingservice.common.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ping.services.onboardingservice.exceptions.OnboardingException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String phoneNumber;
    private String email;
    private String userName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private LocalDateTime lastAccessed;
    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private UserCategory userCategory;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_contact",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private Contact contact;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_personal-info",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "personal-info_id"))
    private PersonalInfo personalInfo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_business",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "business_id"))
    private Business business;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @MapKeyClass(SocialProfileType.class)
//    private Map<SocialProfileType, SocialProfile> socialProfiles;
//
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "contacts",
//            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"))
//    private Map<String, Contact> contacts;
//
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "blocked_contacts",
//            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"))
//    private Set<Contact> blockedContacts;

//    private ContactTrie contactTrie;
}