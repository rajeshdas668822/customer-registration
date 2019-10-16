package org.spring.cloud.demo.registration.controller;


import org.spring.cloud.demo.registration.model.Customer;
import org.spring.cloud.demo.registration.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
public class CustomController {

    private CustomerRepository repository;

    public CustomController(CustomerRepository repository){
        this.repository = repository;
    }

    @GetMapping("/")
    @Transactional
    public Flux<Customer> findCustomers(){
       return repository.findAll();
    }

}
