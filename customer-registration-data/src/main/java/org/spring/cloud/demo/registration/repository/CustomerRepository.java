package org.spring.cloud.demo.registration.repository;

import org.spring.cloud.demo.registration.entity.insurance.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

   // public List<Customer> findByCriteria(CriteriaBuilder criteriaBuilder);
}
