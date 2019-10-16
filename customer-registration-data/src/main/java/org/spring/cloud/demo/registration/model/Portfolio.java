package org.spring.cloud.demo.registration.model;

import lombok.Data;

import java.util.List;

@Data
public class Portfolio {
    private List<Policy> policyList;
}
