package org.spring.cloud.demo.registration.repository;

import org.junit.Test;
import org.spring.cloud.demo.registration.AbstractJpaTest;
import org.spring.cloud.demo.registration.entity.insurance.Customer;
import org.spring.cloud.demo.registration.repository.generic.DefaultRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class AuditEntityTest extends AbstractJpaTest {

    @Autowired
    DefaultRepository defaultRepository;

    @Test
    public  void testCustomerAudit(){
        assertNotNull(defaultRepository);
       List<Customer> customers = defaultRepository.getAllAudit(Customer.class);
        assertNotNull(customers);

    }
}
