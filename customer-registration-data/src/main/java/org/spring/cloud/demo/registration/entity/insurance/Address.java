package org.spring.cloud.demo.registration.entity.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.spring.cloud.demo.registration.entity.AuditBase;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of ={"customer.customerRef"})
@Table(name = "cust_address")
@Audited
public class Address extends AuditBase<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id")
    @SequenceGenerator(name = "address_id", sequenceName = "address_seq")
    @Column(name = "address_id", unique = true)
    private Integer addressId;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "pincode")
    private String pinCode;

    @Column(name = "state")
    private String state;

    @Column(name = "unit")
    private String unit;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



}
