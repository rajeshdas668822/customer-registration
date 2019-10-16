package org.spring.cloud.demo.registration.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {

    private String city;
    private String country;
    private String pinCode;
    private String state;
    private String unit;

}
