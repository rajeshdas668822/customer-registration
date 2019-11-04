package org.spring.cloud.demo.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cust_policy")
public class Policy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_id")
    @SequenceGenerator(name = "policy_id", sequenceName = "policy_seq")
    @Column(name = "policy_id", unique = true)
    private int policyId;


    @Column(name = "policy_ref", length = 128, nullable = false, unique = true)
    private String policyRef;

    @Column(name = "customer_ref")
    private String customerRef;

    @Column(name = "policy_status")
    private String policyStatus;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "sumassured")
    private double sumAssured;

    @Column(name = "policy_term")
    private int policyTerm;

    @Column(name = "premium_payment_term")
    private int premiumPaymentTerm;
    @Column(name = "premium_amount")
    private double premiumAmount;
    @Column(name = "payment_mode")
    private String paymentMode;
    @Column(name = "effective_date")
    private LocalDate effectiveDate;
    @Column(name = "maturity_date")
    private LocalDate maturityDate;



}
