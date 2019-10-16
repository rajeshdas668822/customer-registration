package org.spring.cloud.demo.registration.repository;

import org.spring.cloud.demo.registration.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {
}
