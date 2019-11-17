package org.spring.cloud.demo.registration.entity.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.spring.cloud.demo.registration.entity.AuditBase;
import org.spring.cloud.demo.registration.entity.product.Product;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of ={"policyRef"})
@Table(name = "cust_policy_coverage")
@Audited
public class PolicyCoverage extends AuditBase<String>  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_coverage_id")
    @SequenceGenerator(name = "policy_coverage_id", sequenceName = "policy_coverage_seq")
    @Column(name = "coverage_id", unique = true)
    private Integer coverageId;

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

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

   // private List<Product> coverProducts;


}
