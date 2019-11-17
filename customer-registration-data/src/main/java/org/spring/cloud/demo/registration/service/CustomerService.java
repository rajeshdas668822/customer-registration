package org.spring.cloud.demo.registration.service;

import org.spring.cloud.demo.registration.entity.insurance.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);

}
