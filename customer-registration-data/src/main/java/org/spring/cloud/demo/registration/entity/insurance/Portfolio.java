package org.spring.cloud.demo.registration.entity.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.spring.cloud.demo.registration.entity.AuditBase;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cust_portfolio")
@Audited
@ToString(of ={"customer.customerRef","portfolioId"})
public class Portfolio extends AuditBase<String>  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portfolio_id")
    @SequenceGenerator(name = "portfolio_id", sequenceName = "portfolio_seq")
    @Column(name = "portfolio_id", unique = true)
    private Integer portfolioId;

    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "portfolio")
    private List<Policy> policyList;


}
