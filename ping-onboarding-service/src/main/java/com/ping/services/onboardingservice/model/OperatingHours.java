package com.ping.services.onboardingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "operating-hours")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatingHours {

    @Id
    private Integer id;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
