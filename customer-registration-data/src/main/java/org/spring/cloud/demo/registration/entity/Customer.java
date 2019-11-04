package org.spring.cloud.demo.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cust_details")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id")
    @SequenceGenerator(name = "customer_id", sequenceName = "customer_seq")
    @Column(name = "customer_id", unique = true)
    private String customerId;

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

}
