package org.spring.cloud.demo.registration.service;

import org.spring.cloud.demo.registration.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> createCustomer(Customer customer);

}
