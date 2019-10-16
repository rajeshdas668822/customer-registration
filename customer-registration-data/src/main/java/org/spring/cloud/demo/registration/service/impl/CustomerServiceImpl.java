package org.spring.cloud.demo.registration.service.impl;

import org.spring.cloud.demo.registration.model.Customer;
import org.spring.cloud.demo.registration.repository.CustomerRepository;
import org.spring.cloud.demo.registration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Mono<Customer> createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
