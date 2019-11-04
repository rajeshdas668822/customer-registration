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
@Table(name = "cust_address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id")
    @SequenceGenerator(name = "address_id", sequenceName = "address_seq")
    @Column(name = "address_id", unique = true)
    private String addressId;

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

}
