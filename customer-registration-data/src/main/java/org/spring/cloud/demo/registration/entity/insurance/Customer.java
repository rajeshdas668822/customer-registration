package org.spring.cloud.demo.registration.entity.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.spring.cloud.demo.registration.entity.AuditBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of ={"customerRef","fistName","lastName"})
@Table(name = "cust_details")
@Audited
public class Customer extends AuditBase<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id")
    @SequenceGenerator(name = "customer_id", sequenceName = "customer_seq")
    @Column(name = "customer_id", unique = true)
    private Integer customerId;

    @Column(name = "customer_ref", length = 128, nullable = false, unique = true)
    private String customerRef;

    @Column(name = "first_name")
    private String fistName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "home_phone")
    private String homePhone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Address> address;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private Portfolio portfolio;
}
