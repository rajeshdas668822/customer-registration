package org.spring.cloud.demo.registration.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Policy implements Serializable {
    private String policyRef;
    private int policyId;
    private String customerRef;
    private String policyStatus;
    private String productType;
    private double sumAssured;
    private int policyTerm;
    private int premiumPaymentTerm;
    private double premiumAmount;
    private String paymentMode;
    private LocalDate effectiveDate;
    private LocalDate maturityDate;



}
