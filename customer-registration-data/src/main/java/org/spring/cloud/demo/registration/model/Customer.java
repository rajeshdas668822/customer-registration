package org.spring.cloud.demo.registration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("customer")
public class Customer implements Serializable {
    @Id
    @Column("customer_id")
    private String customerId;
    @Column("first_name")
    private String fistName;
    @Column("last_name")
    private String lastName;
    @Column("email")
    private String email;

   /* @Transient
    private Address address;*/
    @Column("mobile")
    private String mobile;
    @Column("home_phone")
    private String homePhone;

}
