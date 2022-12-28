package com.ping.services.onboardingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ping.services.onboardingservice.common.BusinessSize;
import com.ping.services.onboardingservice.common.Days;
import com.ping.services.onboardingservice.common.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "business")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String businessName;
    private String businessDescription;
    private Tag tag;
    private BusinessSize businessSize;

    @OneToMany
    @MapKeyClass(Days.class)
    private Map<Days, OperatingHours> openHours;

    @OneToOne(mappedBy = "business")
    @JsonIgnore
    private Account account;
}
