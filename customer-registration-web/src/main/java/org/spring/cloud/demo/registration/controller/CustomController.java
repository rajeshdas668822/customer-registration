package org.spring.cloud.demo.registration.controller;


import org.spring.cloud.demo.registration.entity.Customer;
import org.spring.cloud.demo.registration.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CustomController {

    private CustomerRepository repository;

    public CustomController(CustomerRepository repository){
        this.repository = repository;
    }

    @GetMapping("/")
    @Transactional
    public List<Customer> findCustomers(){
       return repository.findAll();
    }

}
