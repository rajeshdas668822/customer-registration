package org.spring.cloud.demo.registration.service.impl;

import org.spring.cloud.demo.registration.entity.insurance.Customer;
import org.spring.cloud.demo.registration.repository.generic.DefaultRepository;
import org.spring.cloud.demo.registration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    DefaultRepository defaultRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return defaultRepository.insert(customer);
    }
}
