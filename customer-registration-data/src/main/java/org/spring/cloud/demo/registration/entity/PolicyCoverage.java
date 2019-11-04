package org.spring.cloud.demo.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cust_policy_coverage")
public class PolicyCoverage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_coverage_id")
    @SequenceGenerator(name = "policy_coverage_id", sequenceName = "policy_coverage_seq")
    @Column(name = "coverage_id", unique = true)
    private int coverageId;

    @Column(name = "policy_ref")
    private String policyRef;

    @Column(name = "rider_name")
    private String riderName;

   // private Policy policy;
    @Column(name = "coverage_start_date")
    private LocalDate coverageStartDate;
    @Column(name = "coverage_end_date")
    private LocalDate coverageEndDate;
    @Column(name = "term")
    private  int term;
    @Column(name = "extra_premium")
    private double extraPremium;

    @Column(name = "sum_assured")
    private double sumAssured;
}
