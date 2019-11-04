package org.spring.cloud.demo.registration.entity;

import lombok.Data;

import java.util.List;

@Data
public class Portfolio {
    private List<Policy> policyList;
}
