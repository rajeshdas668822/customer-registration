package org.spring.cloud.demo.registration.entity.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.spring.cloud.demo.registration.entity.AuditBase;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of ={"customerRef","policyRef"})
@Table(name = "cust_policy")
@Audited
public class Policy extends AuditBase<String>  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_id")
    @SequenceGenerator(name = "policy_id", sequenceName = "policy_seq")
    @Column(name = "policy_id", unique = true)
    private Integer policyId;


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

    @ManyToOne
    @JoinColumn(name = "portfolio_id",updatable = false,insertable = false)
    private Portfolio portfolio;

    @OneToMany(mappedBy = "policy")
    private List<PolicyCoverage> policyCoverages;
}
