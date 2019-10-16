package org.spring.cloud.demo.registration.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PolicyCoverage {
    private String riderName;
    private Policy policy;
    private LocalDate coverageStartDate;
    private LocalDate coverageEndDate;
    private  int term;
    private double extraPremium;
    private double sumAssured;
}
